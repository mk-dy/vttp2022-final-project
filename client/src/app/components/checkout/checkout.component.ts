import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { Subscription } from 'rxjs';
import { PaymentInfo } from 'src/app/common/payment-info';
import { Purchase } from 'src/app/common/purchase';
import { FinalProduct, Order, OrderItem, User } from 'src/app/models';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { CheckoutService } from 'src/app/services/checkout.service';
import { PaymentService } from 'src/app/services/payment.service';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  @ViewChild(MatAccordion)
  accordion!: MatAccordion;

  sub$!: Subscription

  checkoutForm!: FormGroup

  totalPrice: number = 0
  totalQuantity: number = 0
  creditCardYears: number[] = []
  creditCardMonths: number[] = []
  cartItems!: FinalProduct[]
  isDisabled: boolean = false
  email!: string

  userDetails!: User

  storage: Storage = sessionStorage;

  // initialise stripe api
  stripe = Stripe(environment.stripePubKey);

  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = "";
  
  constructor(private fb: FormBuilder, 
    private paymentSvc: PaymentService, 
    private productSvc: ProductService, 
    private checkoutSvc: CheckoutService,
    private cartSvc: CartService,
    private authSvc: AuthService) { }

  ngOnInit(): void {
    this.reviewCartDetails()
    this.checkoutForm = this.createForm()
    this.sub$ = this.productSvc.onGetCartItems.subscribe(data => {
      this.cartItems = data
    })
    this.setupStripePaymentForm()
    this.email = JSON.parse(sessionStorage.getItem('auth-user')!)
    // this.userDetails = this.authSvc.getUserFromEmail(this.email)
  }

  createForm() {
    const data = this.fb.group({
      shippingAddress: this.fb.group({
        street: this.fb.control('', [ Validators.required, this.noWhitespace ]),
        postalCode: this.fb.control('', [ Validators.required, Validators.minLength(6), this.noWhitespace ])
      }),
      billingAddress: this.fb.group({
        street: this.fb.control('', [ Validators.required, this.noWhitespace ]),
        postalCode: this.fb.control('', [ Validators.required, Validators.minLength(6), this.noWhitespace ])
      })
    })
    return data
  }



  get shippingAddressStreet() { 
    return this.checkoutForm.get('shippingAddress.street')
  }

  get shippingAddressPostalCode() { 
    return this.checkoutForm.get('shippingAddress.postalCode')
  }

  get billingAddressStreet() { 
    return this.checkoutForm.get('billingAddress.street')
  }

  get billingAddressPostalCode() { 
    return this.checkoutForm.get('billingAddress.postalCode')
  }

  copyShipAddToBillAdd(event: any) {

    if (event.target.checked) {
      this.checkoutForm.controls['billingAddress']
            .setValue(this.checkoutForm.controls['shippingAddress'].value)
    }
    else {
      this.checkoutForm.controls['billingAddress'].reset();
    }
    
  }


  setupStripePaymentForm() {

    // get a handle to stripe elements
    var elements = this.stripe.elements();

    // Create a card element ... and hide the zip-code field
    this.cardElement = elements.create('card', { hidePostalCode: true });

    // Stripe's UI will be 'mounted' or added into our form, so we do not have to create an input of our own
    this.cardElement.mount('#card-element');

    // Add event binding for the 'change' event on the card element
    this.cardElement.on('change', (event: any) => {

      // get a handle to card-errors element
      this.displayError = document.getElementById('card-errors');

      if (event.complete) {
        this.displayError.textContent = "";
      } else if (event.error) {
        // show validation error to customer
        this.displayError.textContent = event.error.message;
      }

    });

  }

  onSubmit() {

    // AFTER STRIPE
    // ============

    console.log("Handling the submit button");

    if (this.checkoutForm.invalid) {
      this.checkoutForm.markAllAsTouched();
      return;
    }

    // set up order
    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalQuantity = this.totalQuantity;

    // get cart items
    const cartItems = this.cartSvc.cartItems;
    let orderItems: OrderItem[] = [];
    for (let i=0; i < cartItems.length; i++) {
      orderItems[i] = new OrderItem(cartItems[i]);
    }


    let purchase = new Purchase();
    // populate purchase - customer
    let userDetails = JSON.parse(sessionStorage.getItem('userDetails')!)
    purchase.user.id = userDetails.id // ERROR Cannot set properties of undefined
    console.info('>>>>> check purchase userDetails', purchase.user.id)
    purchase.user.firstName = userDetails.firstName // ERROR Cannot set properties of undefined
    console.info('>>>>> check purchase userDetails', purchase.user.firstName)
    purchase.user.lastName = userDetails.lastName
    console.info('>>>>> check purchase userDetails', purchase.user.lastName)
    purchase.user.email = userDetails.email
    console.info('>>>>> check purchase userDetails', purchase.user.email)
    purchase.user.mobile = userDetails.mobile
    console.info('>>>>> check purchase userDetails', purchase.user.mobile)

    // populate purchase - shipping address
    purchase.shippingAddress = this.checkoutForm.controls['shippingAddress'].value;
    console.info('>>>>> check purchase shippingAddress', purchase.shippingAddress)
    // populate purchase - billing address
    purchase.billingAddress = this.checkoutForm.controls['billingAddress'].value;
    console.info('>>>>> check purchase billingAddress', purchase.billingAddress)
    // populate purchase - order and orderItems
    purchase.order = order;
    console.info('>>>>> check purchase order', purchase.order)
    purchase.orderItems = orderItems;
    console.info('>>>>> check purchase orderItems', purchase.orderItems)

    // compute payment info
    this.paymentInfo.amount = Math.round(this.totalPrice * 100); // because stripe looks at cents
    this.paymentInfo.currency = "SGD"; 
    this.paymentInfo.receiptEmail = purchase.user.email;

    console.info('>>>>> check paymentInfo amount', this.paymentInfo.amount)
    console.info('>>>>> check paymentInfo currency',  this.paymentInfo.currency )
    console.info('>>>>> check paymentInfo receiptEmail', this.paymentInfo.receiptEmail )


    // if valid form then
    // - create payment intent
    // - confirm card payment
    // - place order

    if (!this.checkoutForm.invalid && this.displayError.textContent === '') {
      this.isDisabled = true;

      this.checkoutSvc.createPaymentIntent(this.paymentInfo).subscribe(
        (paymentIntentResponse) => {
          this.stripe.confirmCardPayment(paymentIntentResponse.client_secret,
            {
              payment_method: {
                card: this.cardElement,
                billing_details: {
                  email: purchase.user.email,
                  name: `${purchase.user.firstName} ${purchase.user.lastName}`,
                  address: {
                    line1: purchase.billingAddress.street,
                    // city: purchase.billingAddress.city,
                    // state: purchase.billingAddress.state,
                    postal_code: purchase.billingAddress.postalCode,
                    // country: this.billingAddressCountry.value.code
                  }
                }
              }
            }, { handleActions: false })
          .then((result: any) => {
            if (result.error) {
              // alert if there's an error
              alert(`There was an error: ${result.error.message}`)
              this.isDisabled = false;
            } else {
              // place order if no error
              this.checkoutSvc.placeOrder(purchase).subscribe({
                next: response => {
                  alert(`Your order has been received.\nOrder tracking number: ${response.orderTrackingNumber}`);

                  // reset cart
                  this.resetCart();
                  this.isDisabled = false;
                },
                error: err => {
                  alert(`There was an error: ${err.message}`);
                  this.isDisabled = false;
                }
              })
            }            
          }
          // .bind(this)
          );
        }
      );
    } else {
      this.checkoutForm.markAllAsTouched();
      return;
    }

  }

  resetCart() {
    // reset cart data
    this.cartSvc.cartItems = [];
    this.cartSvc.totalPrice.next(0);
    this.cartSvc.totalQuantity.next(0);
    this.cartSvc.persistCartItems();
    // reset the form
    this.checkoutForm.reset();

  }


  reviewCartDetails() {

    // get total quantity
    this.cartSvc.totalQuantity.subscribe(
      totalQuantity => this.totalQuantity = totalQuantity
    );

    // get total price
    this.cartSvc.totalPrice.subscribe(
      totalPrice => this.totalPrice = totalPrice
    );

  }

  noWhitespace(control: FormControl) : ValidationErrors {
        
    // check if string only contains whitespace
    if ((control.value != null) && (control.value.trim().length === 0)) {
        // invalid i.e. whitespace is used, return error object
        return { 'noWhitespace': true };
    }
    else {
        // valid, return null
        return null!;
    }
}

  


}
