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
  name!: string
  constructor(private route: Router, private tokenStorageSvc: TokenStorageService) { }

  
  ngOnInit(): void {
    // this.token = window.sessionStorage.getItem('auth-token');
    this.token = this.tokenStorageSvc.getToken()
    console.info('token in nav',this.token)
    const user = JSON.parse(window.sessionStorage.getItem('userDetails')!).firstName;
    this.name = user


  }

  onIcon() {
    this.iconNavigate()
  }

  onRegister() {
    this.route.navigate(['/signup'])
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
    window.localStorage.removeItem('cartItems')
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
    if (this.noToken()) {
      this.route.navigate(['/'])
    } else {
      this.route.navigate(['/shop'])
    } 
  }

  toLanding() {
    this.route.navigate(['/'])
  }

  toMain() {
    location.reload()
    this.route.navigate(['/shop'])
    
  }
}
