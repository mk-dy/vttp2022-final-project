import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Observable, Subject } from "rxjs";
import { Purchase } from "../common/purchase";
import { PaymentInfo } from "../models";


@Injectable()
export class CheckoutService {

    private purchaseUrl = '/api/checkout/purchase'
    private paymentIntentUrl = '/api/checkout/payment-intent'

    constructor(private http: HttpClient) {}

  placeOrder(purchase: Purchase): Observable<any> {
    return this.http.post<Purchase>(this.purchaseUrl, purchase);    
  }

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.http.post<PaymentInfo>(this.paymentIntentUrl, paymentInfo);
  }
    

}