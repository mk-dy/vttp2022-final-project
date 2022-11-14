import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom, Observable, Subject } from 'rxjs';
import { OrderResponse, User, UserResponse } from '../models';


@Injectable({
  providedIn: 'root'
})
export class OrderService {


    OrderList = new Subject<OrderResponse>()

    constructor(private http: HttpClient) { }

    // getOrders(userId: string) {
    //     return this.http.get(`my-orders/${userId}`)

    // }

    getOrders(userId: string) {

        return firstValueFrom(
            this.http.get(`my-orders/${userId}`))
        
    }

}