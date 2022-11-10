import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Fabric, FinalProduct, Product, User } from "../models";

@Injectable()
export class PaymentService {

    // // We load  Stripe with publishable key
    // stripePromise = loadStripe(environment.stripe);

    // constructor(private http: HttpClient) {}

    // payment(stripe: Stripe | null) {
    //     const payment = {
    //         name: 'Iphone',
    //         currency: 'usd',
    //         // amount on cents *10 => to be on dollar
    //         amount: 99900,
    //         quantity: '1',
    //         cancelUrl: 'http://localhost:4200/payment-cancel', // needs to be a full url
    //         successUrl: 'http://localhost:4200/payment-success', // needs to be a full url, cannot be /payment-cancel
    //       };
      
          
      
    //       // this is a normal http calls for a backend api
    //       this.http
    //         .post('/api/payment', payment)
    //         .subscribe((data: any) => {
    //           // I use stripe to redirect To Checkout page of Stripe platform
    //           stripe!.redirectToCheckout({
    //             sessionId: data.id,
    //           });
    //         });
    // }

   
    
    

    // ALT method
    // When you will enter credit card info and press submit button,
    // chargeCreditCard() method will be called and token will be generated on Stripe side. 
    // If some info will be invalid then error message will be received.
    // chargeCard(token: string) {

    //     const headers = new HttpHeaders()
    //         .set('token', token)
    //         .set('amount', '100')

    //     this.http.post('/payment/charge', {}, {headers: headers})
    //     .subscribe(resp => {
    //         console.log(resp);
    //     })
    // }   

}