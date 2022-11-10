import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { Fabric, FinalProduct, Product, User } from "../models";

@Injectable()
export class ProductService {

    onShowProducts = new Subject<Product[]>()
    onShowChalkbag = new Subject<Product>()
    onShowChalkbucket = new Subject<Product>()
    onGetFabric = new Subject<Fabric[]>()
    onGetFinalProductList = new Subject<FinalProduct[]>()
    onGetCartItems = new Subject<FinalProduct[]>()
    product!: Product

    constructor(private http: HttpClient) {}

    showProducts() {

        return firstValueFrom(
            this.http.get('/shop')
        ).then(result => 
            this.onShowProducts.next(result as Product[]))
    }

    getChalkbag() {

        return firstValueFrom(
            this.http.get('/product/chalkbag')
        ).then(result => 
            this.onShowChalkbag.next(result as Product))
    
    }

    getChalkbucket() {

        return firstValueFrom(
            this.http.get('/product/chalkbucket')
        ).then(result => 
            this.onShowChalkbucket.next(result as Product))
    
    }

    getFabric() {
        return firstValueFrom(
            this.http.get('/fabric')
        )
    }

    getFabricTest() {
        return firstValueFrom(
            this.http.get('/fabricTest')
        ).then(result =>
            console.info('>>>> check fabrictest: ', result))
    }

    addToCart(data: any) {

        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')
        
        return firstValueFrom(
            this.http.post('/cart', data, { headers })
        )

    }

    getCart() {
        return firstValueFrom(
            this.http.get('/cart')
        ).then(result => {
            this.onGetCartItems.next(result as FinalProduct[])
        })
    }


}