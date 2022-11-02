import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { Product, User } from "../models";

@Injectable()
export class SearchService {

    onSearchResult = new Subject<Product[]>()

    constructor(private http: HttpClient) {}

    findProduct(query: string) {

        const params = new HttpParams()
            .set('query',query)

        return firstValueFrom(
            this.http.get('/search', { params })
        ).then(result => {
            this.onSearchResult.next(result as Product[])
        })
    }
    
    

}