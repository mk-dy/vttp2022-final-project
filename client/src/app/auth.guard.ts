import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})

// we use an auth guard to prevent anyone from manually typing in
// a link to forcefully enter a component
// more info: https://www.youtube.com/watch?v=pZn8mCAuBDU

export class AuthenticationGuard implements CanActivate {

    constructor(private authSvc: AuthService, private route: Router) {}

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {

            return this.isLoggedIn();
        }
    
    isLoggedIn(): boolean {
        if (this.authSvc.isLoggedIn()) {
            return true;
        } else {
            this.route.navigate([ '/login' ])
            return false;
        }
    }
}