import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/user";


@Injectable({
  providedIn: 'root'
})
export class SignupuserService {

  private  baseUrl="http://localhost:8081/register";
  constructor(private httpClient:HttpClient) { }
  signupUser(user: User):Observable<object>{

    return this.httpClient.post(`${this.baseUrl}`,user);
  }
}
