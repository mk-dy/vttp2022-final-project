import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import { distinctUntilChanged, Subscription } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { CartItem, Fabric, Product } from '../../models';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-bag',
  templateUrl: './bag.component.html',
  styleUrls: ['./bag.component.css']
})
export class BagComponent implements OnInit, OnDestroy {

  productForm!: FormGroup
  sub$!: Subscription
  chalkbag!: Product
  fabricList!: Fabric[]
  totalPrice!: number

  userId!: string
  
  // if 

  constructor(private fb: FormBuilder, private productSvc: ProductService, private tokenStorageSvc: TokenStorageService,
    private cartSvc: CartService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageSvc.getUser().id
    console.info("userid check",this.userId)
    // this.productSvc.getFabricTest()
    console.info('>>>> fabric test check ends here')
    this.callGetChalkbag()
    this.productSvc.getFabric().then(result =>
      this.fabricList = result as Fabric[])
    this.productForm = this.createForm()

    // this.productForm.controls['withBoot'].valueChanges.subscribe(val => {
    //   if (this.productForm.controls['withBoot'].value == 'yes') { // for setting validations
    //     this.productForm.controls['withBoot'].setValidators(Validators.required);
    //     this.productForm.controls['baseBagDesign'].clearValidators();
    //     this.productForm.controls['withBoot'].updateValueAndValidity();
    //     console.info(">>>>>>invalid? base" + this.productForm.controls['baseBagDesign'].invalid)
    //   } else {
    //     this.productForm.controls['baseBagDesign'].setValidators(Validators.required);
    //     this.productForm.controls['withBoot'].clearValidators();
    //     this.productForm.controls['withBoot'].updateValueAndValidity();
    //       console.info(">>>>>>invalid? boot" + this.productForm.controls['withBoot'].invalid)
    //   }
    // })
    
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

  createForm(): FormGroup {
    const myFormGroup = this.fb.group({
        withBoot: this.fb.control<string>('', Validators.required), // yes or no
        upsize: this.fb.control<string>('', Validators.required), // yes or no
        hoopStraps: this.fb.control<string>('', Validators.required), // yes or no
        keychainHolders: this.fb.control<string>('', Validators.required), 
        // keychainNum shows up when keychainHolders is yes
        keychainNum: this.fb.control<number | null>(1), // custom validator for this
        exteriorDesign: this.fb.control<string>('', Validators.required),
        baseBagDesign: this.fb.control<string>('', Validators.required), // if WITHOUT BOOT // issue with validity here
        bootDesign: this.fb.control<string>('', Validators.required), // if WITH BOOT // issue with validity here
        quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)]),
        remarks: this.fb.control<string>('')
      })
    
    
    

    return myFormGroup

    // return this.fb.group({
    //   bootType: this.fb.control<string>('', Validators.required), // yes or no
    //   upsize: this.fb.control<string>('', Validators.required), // yes or no
    //   hoopStraps: this.fb.control<string>('', Validators.required), // yes or no
    //   keychainHolders: this.fb.control<string>('', Validators.required), 
    //   // keychainNum shows up when keychainHolders is yes
    //   keychainNum: this.fb.control<number | null>(1),
    //   exteriorDesign: this.fb.control<string>('', Validators.required),
    //   baseDesign: this.fb.control<string>('', Validators.required), // for no boot
    //   bootDesign: this.fb.control<string>('', Validators.required), // for with boot
    //   quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)]),
    //   remarks: this.fb.control<string>('')
    // })
  }

  processForm(formDirective: FormGroupDirective) {
    const data = this.productForm.value
    console.info('>>>> check data: ', data)
    if (data.keychainHolders === 'no' || data.keychainNum === null) {
      data.keychainNum = 0
    }
    formDirective.resetForm();
    this.productForm = this.createForm()
    // data['prodId'] = 'CHLKBAG01'
    data['imgLink'] = this.chalkbag.imgLink
    console.info('>>>> check data again: ', data)
    console.info(">>> START check total price: ", this.totalPrice)
    // if criteria met, add $
  // this.totalPrice = (data.quantity * this.totalPrice) // TEMPORARILY OFF
    // console.info(">>> END check total price: ", this.totalPrice)
    data['price'] = this.totalPrice // unit price currently
    data['userId'] = this.userId

    if (data.upsize === 'yes') {
      this.totalPrice += 15.00 
    }
    if (data.hoopStraps === 'yes') {
      this.totalPrice += 3.50 
    }
    if (data.keychainHolders === 'yes') {
      this.totalPrice = this.totalPrice + (data.keychainNum * 1.00)
    }
    // create prod id here
    let prodId = ''
    if (data.withBoot === 'yes') {
      prodId = prodId.concat('Byes')
    } else {
      prodId = prodId.concat('Bno') 
    }
    if (data.upsize === 'yes') {
      prodId = prodId.concat('Uyes')
    } else {
      prodId = prodId.concat('Uno')
    }
    if (data.hoopStraps === 'yes') {
      prodId = prodId.concat('Hyes')
    } else {
      prodId = prodId.concat('Hno')
    }
    if (data.keychainHolders === 'yes') {
      prodId = prodId.concat('Kyes')
    } else {
      prodId = prodId.concat('Kno')
    }
    if (data.keychainNum > 0) {
      prodId = prodId.concat('K',data.keychainNum)
    } else {
      prodId = prodId.concat('')
    }
    if (data.exteriorDesign !== '') {
      prodId = prodId.concat(data.exteriorDesign)
    } else {
      prodId = prodId.concat('')
    }
    if (data.baseBagDesign !== '') {
      prodId = prodId.concat(data.baseBagDesign)
    } else {
      prodId = prodId.concat('')
    }
    if (data.bootDesign !== '') {
      prodId = prodId.concat(data.bootDesign)
    } else {
      prodId = prodId.concat('')
    }
    console.info('ridiculous prodId but if it works, it works: ', prodId)
    data['prodId'] = prodId


    
    // trying to store into local storage

    const theCartItem = new CartItem(data);
    this.cartSvc.addToCart(theCartItem);


    // temporarily stop this method first, testing local storage
    // this.productSvc.addToCart(data)
    

    // need to reset totalPrice after processForm()
    this.callGetChalkbag()
  }

  callGetChalkbag() {
    this.productSvc.getChalkbag()
    this.sub$ = this.productSvc.onShowChalkbag.subscribe( data => {
      this.chalkbag = data
      this.totalPrice = this.chalkbag.price
    })
  }

  // changeValidators() {
    
  //   console.log(this.productForm.get("bootType").value)
 
  //   if (this.productForm.get("bootType").value=="yes") {
  //     this.productForm.controls["bootDesign"].setValidators(Validators.required);
  //     this.productForm.controls["baseDesign"].clearValidators();
  //   } else {
  //     this.productForm.controls["bootDesign"].clearValidators();
  //     this.productForm.controls["baseDesign"].setValidators(Validators.required);
  //   }
 
  //   this.productForm.get("bootDesign").updateValueAndValidity();
  //   this.productForm.get("baseDesign").updateValueAndValidity();
 
       
  // }

}
