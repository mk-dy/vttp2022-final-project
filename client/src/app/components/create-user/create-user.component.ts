import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
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
  isLoggedIn = false
  isLoginFailed = false
  errorMessage =''
  

  constructor(private fb: FormBuilder,
     private authSvc: AuthService,
     private router: Router,
     private tokenStorageSvc: TokenStorageService) { }

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
    const loginData = this.userForm.value as User
    console.info('>>> check user: ', loginData)
    // send the user details over using service
    this.authSvc.createUser(loginData).then(response => {
      const result = response as CreationMsg
      this.creationMsg = result.message + ' You will be re-directed to the main page shortly.'
      console.info(">>>> CHECK SUCCESS message", this.creationMsg)
      if (this.creationMsg.includes('SUCCESS')) {
        // auto login and redirect if success
        this.authSvc.login(loginData).subscribe(
          data => {
            this.tokenStorageSvc.saveToken(data.accessToken);
            this.tokenStorageSvc.saveUser(data);
    
            this.isLoginFailed = false;
            this.isLoggedIn = true;

            // if success, show a success message on the bottom
            // use auth service to get token,
            // wait a while, you will be redirected shortly
            // redirect to shop
            this.routeAfterDuration()
            // this.router.navigate(['/shop'])
            // this.reloadPage();
    
          },
          err => {
            this.errorMessage = err.error.message;
            this.isLoginFailed = true;
          }
        );
        

      }
    })
    .catch(error => {
      console.info(error)
      this.creationMsg = error.error.message;
      console.info(">>>> CHECK ERROR message", this.creationMsg)
      // if fail, produce same message in account creation page
    })
    
  }

  routeAfterDuration() {
    setTimeout(() => {
      this.router.navigate(['/shop']);
  }, 5000);
    // location.reload
  }




}
