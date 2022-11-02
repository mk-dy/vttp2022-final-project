import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../models';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  userForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.userForm = this.createForm()

  }

  createForm() {
    return this.fb.group({
      firstName: this.fb.control<string>('', Validators.required),
      lastName: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      mobile: this.fb.control<string>('', Validators.required),
      password: this.fb.control<string>('', Validators.required)      
    })
  }

  processForm() {
    const data = this.userForm.value as User
    console.info('>>> check user: ', data)
    // send the user details over using service
  }

}
