import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { CreationMsg, User, UserLogin } from "../models";

@Injectable()
export class UserService {

    onCreateAcc = new Subject<string>()

    constructor(private http: HttpClient) {}

    createUser(user: User) {

        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')
        
        // return firstValueFrom(
        //     this.http.post('/signup', user)
        // ).then(result => {
        //     this.onCreateAcc.next(result as string)
        // })
        return firstValueFrom(
            this.http.post('/signup', user, { headers })
        )

    }

    login(userLogin: UserLogin) {

        
    }

}