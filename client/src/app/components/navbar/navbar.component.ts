import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  token!: string | null

  constructor(private route: Router) { }

  ngOnInit(): void {
    this.token = this.getToken()
    console.info('token in nav',this.token)
  }

  onIcon() {
    if (this.token !== null) {
      this.route.navigate(['/shop']);
    } 
    if (this.token === null) {
      this.route.navigate(['/']);
    } 
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

  getToken(): string | null {
    return window.sessionStorage.getItem("auth-token");
  }

}
