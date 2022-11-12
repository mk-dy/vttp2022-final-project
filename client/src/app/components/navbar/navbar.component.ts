import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  token!: string | null

  constructor(private route: Router, private tokenStorageSvc: TokenStorageService) { }

  
  ngOnInit(): void {
    this.token = this.tokenStorageSvc.getToken()
    console.info('token in nav',this.token)
  }

  onIcon() {
    this.iconNavigate()
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

  noToken() {
    if (this.token === null) {
      return true
    } else {
      return false
    }
  }
  
  iconNavigate() {
    if (this.token !== null) {
      this.route.navigate(['/shop']);
    } else {
      this.route.navigate(['/']);
    } 
  }
}
