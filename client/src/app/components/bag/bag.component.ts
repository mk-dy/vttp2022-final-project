import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import { distinctUntilChanged, Subscription } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { CartItem, Fabric, FavMsg, Product } from '../../models';
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
  unitPrice!: number

  favMessage!: string

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
        quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)])
        // remarks: this.fb.control<string>('')
      })
    
    return myFormGroup

  }

  processForm(formDirective: FormGroupDirective) {
    const data = this.productForm.value
    formDirective.resetForm();
    this.productForm = this.createForm()

    console.info('>>>> check data: ', data)
    if (data.keychainHolders === 'no' || data.keychainNum === null) {
      data.keychainNum = 0
    }
    data['imgLink'] = this.chalkbag.imgLink
    console.info('>>>> check data again: ', data)
    console.info(">>> START check total price: ", this.unitPrice)
    
    data['userId'] = this.userId

    if (data.upsize === 'yes') {
      this.unitPrice += 15.00 
    }
    if (data.hoopStraps === 'yes') {
      this.unitPrice += 3.50 
    }
    if (data.keychainHolders === 'yes') {
      this.unitPrice = this.unitPrice + (data.keychainNum * 1.00)
    }
    data['price'] = this.unitPrice // unit price currently
    data['prodId'] = this.changeProdId(data)
    console.info('ridiculous prodId but if it works, it works: ', data['prodId'])
    data['prodName'] = 'Chalk Bag'
    

    const theCartItem = new CartItem(data);
    this.cartSvc.addToCart(theCartItem);

    // need to reset totalPrice after processForm()
    this.callGetChalkbag()
  }

  addFavourite() {
    const data = this.productForm.value
    data['imgLink'] = this.chalkbag.imgLink
    data['prodName'] = 'Chalk Bag'
    data['price'] = this.unitPrice // unit price currently
    data['userId'] = this.userId
    console.info('>>> favourites: ' + data)
    this.productSvc.addToFavourites(data).then(result=> {
      this.favMessage = (result as FavMsg).message
      console.log(this.favMessage)
    })
  }

  changeProdId(data: any) {
    let prodId = ''
    if (data.withBoot === 'yes') {
      prodId = prodId.concat('Boot:yes-')
    } else {
      prodId = prodId.concat('Boot:no-') 
    }
    if (data.upsize === 'yes') {
      prodId = prodId.concat('Up:yes-')
    } else {
      prodId = prodId.concat('Up:no-')
    }
    if (data.hoopStraps === 'yes') {
      prodId = prodId.concat('Hoop:yes-')
    } else {
      prodId = prodId.concat('Hoop:no-')
    }
    if (data.keychainHolders === 'yes') {
      prodId = prodId.concat('Key:yes-')
    } else {
      prodId = prodId.concat('Key:no-')
    }
    if (data.keychainNum > 0) {
      prodId = prodId.concat('KNum:',data.keychainNum,'-')
    } else {
      prodId = prodId.concat('KNum:-')
    }
    if (data.exteriorDesign !== '') {
      prodId = prodId.concat('ExtD:',data.exteriorDesign,'-')
    } else {
      prodId = prodId.concat('ExtD:','-')
    }
    if (data.baseBagDesign !== '') {
      prodId = prodId.concat('BaseD:',data.baseBagDesign,'-')
    } else {
      prodId = prodId.concat('BaseD:','-')
    }
    if (data.bootDesign !== '') {
      prodId = prodId.concat('BootD:',data.bootDesign,'-')
    } else {
      prodId = prodId.concat('BootD:','-')
    }
    
    return prodId;
  }




  callGetChalkbag() {
    this.productSvc.getChalkbag()
    this.sub$ = this.productSvc.onShowChalkbag.subscribe( data => {
      this.chalkbag = data
      this.unitPrice = this.chalkbag.price
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
