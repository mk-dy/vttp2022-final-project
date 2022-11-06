import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { CreationMsg, User } from '../../models';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  userForm!: FormGroup
  creationMsg!: string
  sub$!: Subscription

  constructor(private fb: FormBuilder, private userSvc: UserService, private router: Router) { }

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
    this.userSvc.createUser(data).then(response => {
      const result = response as CreationMsg
      this.creationMsg = result.message
      console.info(">>>> CHECK SUCCESS message", this.creationMsg)
      if (this.creationMsg.includes('SUCCESS')) {
        // if success, redirect to shop page and send an email about successful creation
        this.router.navigate(['/shop'])
      }
    })
    .catch(error => {
      console.info(error)
      this.creationMsg = error.error;
      console.info(">>>> CHECK ERROR message", this.creationMsg)
      // if fail, produce same message in account creation page
    })
    
  }




}
