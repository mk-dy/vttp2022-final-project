import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CartItem, FinalProduct } from 'src/app/models';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  productList!: FinalProduct[]
  sub$!: Subscription
  userId!: string 

  cartItems: CartItem[] = [];
  cartItemsById: CartItem[] = [];
  
  totalPrice: number = 0;
  totalQuantity: number = 0;


  constructor(private productSvc: ProductService, private route: Router, private tokenStorageSvc: TokenStorageService,
    private cartSvc: CartService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageSvc.getUser().id
    console.info("userid check",this.userId)
    // this.sub$ = this.productSvc.onGetCartItems.subscribe(data => {
    //   this.productList = data
    // })
    // this.productSvc.getCart(this.userId).then(result => {
    //   this.productList = result as FinalProduct[]
    // })
    // console.info(">>>> productList: ", this.productList)

    this.listCartDetails()
    
  }

  onCheckout() {
    this.route.navigate(['/checkout'])
  }


  listCartDetails() {

    // need to show the cart of only the user who is logged in


    // get a handle to the cart items
    this.cartItems = this.cartSvc.cartItems;
    for (let item of this.cartItems) {
      if (this.userId === item.userId) {
          console.info('>>>ITEM',item)
          this.cartItemsById.push(item)
          
      }
    }
    this.cartItems = this.cartItemsById
    console.info('CHECKCHECKCHECK: ' + this.cartItems)

    // subscribe to the cart totalPrice
    this.cartSvc.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.cartSvc.totalQuantity.subscribe( 
      data => this.totalQuantity = data
    );

    // compute cart total price and quantity
    this.cartSvc.computeCartTotals();
  }

  incrementQuantity(theCartItem: CartItem) {
    this.cartSvc.incrementQuantity(theCartItem);
  }

  decrementQuantity(theCartItem: CartItem) {
    this.cartSvc.decrementQuantity(theCartItem);
  }

  remove(theCartItem: CartItem) {
    this.cartSvc.remove(theCartItem);
    location.reload();
  }

}
