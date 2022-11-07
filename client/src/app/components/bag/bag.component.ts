import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Fabric, Product } from '../../models';
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
  
  // if 

  constructor(private fb: FormBuilder, private productSvc: ProductService) { }

  ngOnInit(): void {
    // this.productSvc.getFabricTest()
    console.info('>>>> fabric test check ends here')
    this.callGetChalkbag()
    this.productSvc.getFabric().then(result =>
      this.fabricList = result as Fabric[])
      console.info('>>>> fabriclist check: ', this.fabricList)
    this.productForm = this.createForm()

    // this.productForm.get("bootType").valueChanges
    //   .subscribe(data=> {
    //     this.changeValidators()
    //   })
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
        baseBagDesign: this.fb.control<string>('', Validators.required), // for no boot
        bootDesign: this.fb.control<string>('', Validators.required), // for with boot
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

  processForm() {
    const data = this.productForm.value
    console.info('>>>> check data: ', data)
    if (data.keychainHolders === 'no' || data.keychainNum === null) {
      data.keychainNum = 0
    }
    this.productForm = this.createForm()
    data['prodId'] = 'CHLKBAG01'
    console.info('>>>> check data again: ', data)
    console.info(">>> START check total price: ", this.totalPrice)
    // if criteria met, add $
    if (data.upsize === 'yes') {
      this.totalPrice += 15.00 
    }
    if (data.hoopStraps === 'yes') {
      this.totalPrice += 3.50 
    }
    if (data.keychainHolders === 'yes') {
      this.totalPrice = this.totalPrice + (data.keychainNum * 1.00)
    }
    this.totalPrice = (data.quantity * this.totalPrice) 
    console.info(">>> END check total price: ", this.totalPrice)
    data['price'] = this.totalPrice

    this.productSvc.addToCart(data)

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
