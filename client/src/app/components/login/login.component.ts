import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder, 
    private tokenStorageSvc: TokenStorageService, 
    private authSvc: AuthService,
    private route: Router) { }

  ngOnInit(): void {
    if (this.tokenStorageSvc.getToken()) {
      this.isLoggedIn = true;
    }
    this.loginForm = this.createForm()
  }

  createForm(): FormGroup {
    return this.fb.group({
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      password: this.fb.control<string>('', Validators.required )
    })
  }

  processForm() {
    const loginData = this.loginForm.value
    console.info('>>>> check data: ', loginData)

    this.authSvc.login(loginData).subscribe(
      data => {
        this.tokenStorageSvc.saveToken(data.accessToken);
        this.tokenStorageSvc.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.route.navigate(['/shop'])
        // this.reloadPage();

      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );

  }

  reloadPage() {
    window.location.reload()
  }

}
