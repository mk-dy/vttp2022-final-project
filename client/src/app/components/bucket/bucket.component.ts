import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Product, Fabric, CartItem } from 'src/app/models';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-bucket',
  templateUrl: './bucket.component.html',
  styleUrls: ['./bucket.component.css']
})
export class BucketComponent implements OnInit {

  productForm!: FormGroup
  sub$!: Subscription
  chalkbucket!: Product
  fabricList!: Fabric[]
  totalPrice!: number
  userId!: string

  constructor(private fb: FormBuilder, private productSvc: ProductService, private tokenStorageSvc: TokenStorageService, private cartSvc: CartService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageSvc.getUser().id
    console.info("userid check",this.userId)
    // this.productSvc.getFabricTest()
    console.info('>>>> fabric test check ends here')
    this.callGetChalkbucket()
    this.productSvc.getFabric().then(result =>
      this.fabricList = result as Fabric[])
      console.info('>>>> fabriclist check: ', this.fabricList)
    this.productForm = this.createForm()

    // this.productForm.get("bootType").valueChanges
    //   .subscribe(data=> {
    //     this.changeValidators()
    //   })

  }

  createForm(): FormGroup {
    return this.fb.group({
      baseType: this.fb.control<string>('', Validators.required), // half or whole
      frontSideClosure: this.fb.control<string>('', Validators.required), // front or side
      // numOfBrushHolder: this.fb.control<number>(1, [ Validators.min(1), Validators.max(3) ]), // minimum one 
      // brushHolderLocation: this.fb.control<string>('', Validators.required),
      magneticClosure: this.fb.control<string>('', Validators.required), // yes or no
      dRingWebbing: this.fb.control<string>('', Validators.required), // yes or no
      frontPocketDesign: this.fb.control<string>('', Validators.required),
      frontPocketBackDesign: this.fb.control<string>('', Validators.required),
      backDesign: this.fb.control<string>('', Validators.required),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)]),
      remarks: this.fb.control<string>(''),
      // ONLY FOR FULL BASE
      baseBucketDesign: this.fb.control<string>('', Validators.required), // custom validator for this
      
    })
  }

  processForm(formDirective: FormGroupDirective) {
    const data = this.productForm.value
    console.info('>>>> check data: ', data)
    if (data.keychainHolders === 'no' || data.keychainNum === null) {
      data.keychainNum = 0
    }
    formDirective.resetForm();
    // this.productForm.reset()
    this.productForm = this.createForm()
    data['prodId'] = 'CHLKBKT02'
    data['imgLink'] = this.chalkbucket.imgLink
    data['userId'] = this.userId
    console.info('>>>> check data again: ', data)
    console.info(">>> START check total price: ", this.totalPrice)
    // if criteria met, add $
    if (data.frontOrSideClosure === 'side') {
      this.totalPrice += 2.00 
    }
    if (data.magneticClosure === 'yes') {
      this.totalPrice += 5.00 
    }
    if (data.dRingWebbing === 'yes') {
      this.totalPrice += 4.00 
    }
    // this.totalPrice = (data.quantity * this.totalPrice) 
    console.info(">>> END check total price: ", this.totalPrice)
    data['price'] = this.totalPrice

    // create prod id here
    let prodId = ''
    if (data.baseType === 'whole') {
      prodId = prodId.concat('Base:whole-')
    } else {
      prodId = prodId.concat('Base:half-') 
    }
    if (data.frontSideClosure === 'front') {
      prodId = prodId.concat('Close:front-')
    } else {
      prodId = prodId.concat('Close:side-')
    }
    if (data.magneticClosure === 'yes') {
      prodId = prodId.concat('Magnet:yes-')
    } else {
      prodId = prodId.concat('Magnet:no-')
    }
    if (data.dRingWebbing === 'yes') {
      prodId = prodId.concat('DRing:yes-')
    } else {
      prodId = prodId.concat('DRing:no-')
    }
    if (data.frontPocketDesign !== '') {
      prodId = prodId.concat('FrontPock:',data.frontPocketDesign,'-')
    } else {
      prodId = prodId.concat('FrontPock:','')
    }
    if (data.frontPocketBackDesign !== '') {
      prodId = prodId.concat('FrontPockBack:',data.frontPocketBackDesign,'-')
    } else {
      prodId = prodId.concat('FrontPockBack:')
    }
    if (data.backDesign !== '') {
      prodId = prodId.concat('Back:',data.backDesign,'-')
    } else {
      prodId = prodId.concat('Back:-')
    }
    if (data.baseBucketDesign !== '') {
      prodId = prodId.concat('Base:',data.baseBucketDesign,'-')
    } else {
      prodId = prodId.concat('Base:-')
    }
    console.info('ridiculous prodId but if it works, it works: ', prodId)
    data['prodId'] = prodId
    data['prodName'] = 'Chalk Bucket'

    const theCartItem = new CartItem(data);
    this.cartSvc.addToCart(theCartItem);




    // this.productSvc.addToCart(data)
    
    // need to reset totalPrice after processForm()
    this.callGetChalkbucket()
  }

  callGetChalkbucket() {
    this.productSvc.getChalkbucket()
    this.sub$ = this.productSvc.onShowChalkbucket.subscribe( data => {
      this.chalkbucket = data
      this.totalPrice = this.chalkbucket.price
    })
  }

}
