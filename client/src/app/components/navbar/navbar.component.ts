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

}
