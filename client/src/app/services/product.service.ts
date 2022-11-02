import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { Fabric, Product, User } from "../models";

@Injectable()
export class ProductService {

    onShowProducts = new Subject<Product[]>()
    onShowChalkbag = new Subject<Product>()
    onGetFabric = new Subject<Fabric[]>()
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

    getFabric() {
        return firstValueFrom(
            this.http.get('/fabric')
        )
    }


}