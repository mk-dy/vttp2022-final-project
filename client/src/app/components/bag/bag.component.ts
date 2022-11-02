import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Fabric, Product } from '../../models';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-bag',
  templateUrl: './bag.component.html',
  styleUrls: ['./bag.component.css']
})
export class BagComponent implements OnInit {

  productForm!: FormGroup
  sub$!: Subscription
  chalkbag!: Product
  fabricList!: Fabric[]
  
  // if 

  constructor(private fb: FormBuilder, private productSvc: ProductService) { }

  ngOnInit(): void {
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

  createForm(): FormGroup {
    const myFormGroup = this.fb.group({
        bootType: this.fb.control<string>('', Validators.required), // yes or no
        upsize: this.fb.control<string>('', Validators.required), // yes or no
        hoopStraps: this.fb.control<string>('', Validators.required), // yes or no
        keychainHolders: this.fb.control<string>('', Validators.required), 
        // keychainNum shows up when keychainHolders is yes
        keychainNum: this.fb.control<number | null>(1),
        exteriorDesign: this.fb.control<string>('', Validators.required),
        baseDesign: this.fb.control<string>('', Validators.required), // for no boot
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
    console.info('>>>> check data again: ', data)
  }

  callGetChalkbag() {
    this.productSvc.getChalkbag()
    this.sub$ = this.productSvc.onShowChalkbag.subscribe( data => {
      this.chalkbag = data
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
