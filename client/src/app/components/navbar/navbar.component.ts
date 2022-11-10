import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  onRouteLanding() {
    this.route.navigate(['/']);
  }

  onUser() {
    
  }

  onFavourites() {
    this.route.navigate(['/favourites'])
  }

  onCart() {
    this.route.navigate(['/cart'])
  }

  onOrders() {
    this.route.navigate(['/orders'])
  }

  onLogin() {
    this.route.navigate(['/login'])
  }

  async onSignOut() {
    window.sessionStorage.clear();
    await this.route.navigate(['/'])
    this.reloadPage()
  }

  reloadPage() {
    window.location.reload()
  }

}
