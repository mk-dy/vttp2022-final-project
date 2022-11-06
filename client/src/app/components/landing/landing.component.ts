import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onShop() {
    this.router.navigate(['/shop'])
  }

  onLogin() {
    this.router.navigate(['/login'])
  }
  
  onSignUp() {
    this.router.navigate(['/signup'])
  }
  

}
