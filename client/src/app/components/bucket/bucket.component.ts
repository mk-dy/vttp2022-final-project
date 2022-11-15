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
  unitPrice!: number
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
      magneticClosure: this.fb.control<string>('', Validators.required), // yes or no
      dRingWebbing: this.fb.control<string>('', Validators.required), // yes or no
      frontPocketDesign: this.fb.control<string>('', Validators.required),
      frontPocketBackDesign: this.fb.control<string>('', Validators.required),
      backDesign: this.fb.control<string>('', Validators.required),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)]),
      // remarks: this.fb.control<string>(''),
      // ONLY FOR FULL BASE
      baseBucketDesign: this.fb.control<string>('', Validators.required), // custom validator for this
      
    })
  }

  processForm(formDirective: FormGroupDirective) {
    const data = this.productForm.value
    formDirective.resetForm();
    this.productForm = this.createForm()

    console.info('>>>> check data: ', data)

    data['imgLink'] = this.chalkbucket.imgLink
    data['userId'] = this.userId
    console.info('>>>> check data again: ', data)
    console.info(">>> START check total price: ", this.unitPrice)
    // if criteria met, add $
    if (data.frontOrSideClosure === 'side') {
      this.unitPrice += 2.00 
    }
    if (data.magneticClosure === 'yes') {
      this.unitPrice += 5.00 
    }
    if (data.dRingWebbing === 'yes') {
      this.unitPrice += 4.00 
    }

    console.info(">>> END check total price: ", this.unitPrice)
    data['price'] = this.unitPrice
    data['prodId'] = this.changeProdId(data)
    console.info('ridiculous prodId but if it works, it works: ', data['prodId'])
    data['prodName'] = 'Chalk Bucket'

    const theCartItem = new CartItem(data);
    this.cartSvc.addToCart(theCartItem);

    // need to reset totalPrice after processForm()
    this.callGetChalkbucket()
  }

  addFavourite() {
    const data = this.productForm.value
    data['imgLink'] = this.chalkbucket.imgLink
    data['prodName'] = 'Chalk Bucket'
    data['price'] = this.unitPrice
    data['userId'] = this.userId
    console.info('>>> favourites: ' + data)
    this.productSvc.addToFavourites(data).then(result => {
      console.log(result)
    })
  }

  changeProdId(data: any) {
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
    
    return prodId;
  }

  callGetChalkbucket() {
    this.productSvc.getChalkbucket()
    this.sub$ = this.productSvc.onShowChalkbucket.subscribe( data => {
      this.chalkbucket = data
      this.unitPrice = this.chalkbucket.price
    })
  }

}
