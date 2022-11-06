import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Product, Fabric } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';

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

  constructor(private fb: FormBuilder, private productSvc: ProductService) { }

  ngOnInit(): void {
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
      frontOrSideClosure: this.fb.control<string>('', Validators.required), // front or side
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
      baseDesign: this.fb.control<string>('', Validators.required),
      
    })
  }

  processForm() {
    const data = this.productForm.value
    console.info('>>>> check data: ', data)
    if (data.keychainHolders === 'no' || data.keychainNum === null) {
      data.keychainNum = 0
    }
    this.productForm = this.createForm()
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
    this.totalPrice = (data.quantity * this.totalPrice) 
    console.info(">>> END check total price: ", this.totalPrice)

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
