import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loginForm = this.createForm()
  }

  createForm(): FormGroup {
    return this.fb.group({
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]), // half or full base
      password: this.fb.control<string>('', Validators.required ), // yes or no
    })
  }

  processForm() {
    const data = this.loginForm.value;
    console.info('>>>> check data: ', data)
  }

}
