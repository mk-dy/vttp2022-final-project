import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { User, UserLogin } from "../models";

@Injectable()
export class UserService {

    constructor(private http: HttpClient) {}

    createUser(user: User) {
        
        return firstValueFrom(
            this.http.post('/create-user', user)
        )
    }

    login(userLogin: UserLogin) {

        
    }

}