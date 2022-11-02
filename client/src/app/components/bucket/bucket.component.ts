import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-bucket',
  templateUrl: './bucket.component.html',
  styleUrls: ['./bucket.component.css']
})
export class BucketComponent implements OnInit {

  productForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.productForm = this.createForm()

  }

  createForm(): FormGroup {
    return this.fb.group({
      baseType: this.fb.control<string>('', Validators.required), // half or full base
      frontOrSideClosure: this.fb.control<string>('', Validators.required), // yes or no
      numOfBrushHolder: this.fb.control<number>(1, [ Validators.min(1), Validators.max(3) ]), // minimum one 
      brushHolderLocation: this.fb.control<string>('', Validators.required),
      dRingWebbing: this.fb.control<string>('', Validators.required), // yes or no
      frontPocketDesign: this.fb.control<string>('', Validators.required),
      frontPocketBackDesign: this.fb.control<string>('', Validators.required),
      backDesign: this.fb.control<string>('', Validators.required),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)]),
      remarks: this.fb.control<string>(''),
      // ONLY FOR FULL BASE
      baseDesign: this.fb.control<string>('', Validators.required),
      magneticClosure: this.fb.control<string>('', Validators.required) // yes or no
    })
  }

  processForm() {
    const data = this.productForm.value;
    console.info('>>>> check data: ', data)
  }

}
