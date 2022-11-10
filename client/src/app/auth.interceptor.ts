import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';
import { TokenStorageService } from './services/token-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';       

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenStorageSvc: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.tokenStorageSvc.getToken();
    if (token != null) {
      // Bearer token identifies the user that owns it and defines a user session, 
      // A bearer token is a signed temporary replacement for the username/password combination
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];