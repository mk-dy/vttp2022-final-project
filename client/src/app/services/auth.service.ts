import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom, Observable, Subject } from 'rxjs';
import { User, UserResponse } from '../models';

const AUTH_API = '/api/auth';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  UserDetails = new Subject<User>()

  constructor(private http: HttpClient) { }

  login(data: any): Observable<any> {
    return this.http.post(`${AUTH_API}/login`, data, httpOptions);
  }

  createUser(user: User) {

    const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept', 'application/json')
    
    return firstValueFrom(
        this.http.post(`${AUTH_API}/signup`, user, { headers })
    )
  }

  isLoggedIn() {
    return !!sessionStorage.getItem('auth-token')
  }

  getUserFromEmail(email: string) {
    return this.http.get(`${AUTH_API}/getUser/${email}`)
  }
}