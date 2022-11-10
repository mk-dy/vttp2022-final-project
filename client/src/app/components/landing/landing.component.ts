import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private router: Router, private tokenStorageSvc: TokenStorageService) { }

  ngOnInit(): void {
  }

  onShop() {
    this.router.navigate(['/login'])
  }

  getToken(): string | null {
    return window.sessionStorage.getItem("auth-token");
  }

  onCheckout() {
    this.router.navigate(['/checkout'])
  }
  
  onSignUp() {
    this.router.navigate(['/signup'])
  }
  

}
