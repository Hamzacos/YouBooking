import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "../model/user";
import {BehaviorSubject, Observable, tap} from "rxjs";
import {Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";
import {IdToken} from "../model/ IdToken";

@Injectable({
  providedIn: 'root'
})
export class LoginuserService {
  private baseUrl = "http://localhost:8081/login";

  isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn = this.isLoggedInSubject.asObservable();
  userNameSubject = new BehaviorSubject<String>("");
  userLogged = this.userNameSubject.asObservable();
  private refreshTokenInterval:any;
  role!:String;

  constructor(private http:HttpClient,private route:Router,private jwtHelper:JwtHelperService) { }


  signIn(user:User):Observable<IdToken|HttpErrorResponse>{
    const params = new HttpParams()
      .set('username', user.username.toString()).set('password',user.password.toString());
    return this.http.post<IdToken>(this.baseUrl,params).pipe(
      tap(response => {
        localStorage.setItem("accessToken", response.accessToken.toString());
        localStorage.setItem("refresh_token", response.refreshToken.toString());
        this.isLoggedInSubject.next(true);
        const accessToken = localStorage.getItem("accessToken");
        console.log(accessToken)
        if(accessToken){
          let jwt = this.jwtHelper.decodeToken(accessToken).sub;
          this.userNameSubject.next(jwt.username);
          this.startRefreshTokenInterval();
        }
      })
    );
  }

  isLogedIn(): boolean {
    const token = localStorage.getItem('accessToken');
    if(token!=null  && token != undefined){
      const username = this.jwtHelper.decodeToken(token).username
      this.userNameSubject.next(username)
      /*console.log("=============================")
      console.log(user)*/
    }
    const isLoggedIn = !this.jwtHelper.isTokenExpired(token);
    this.isLoggedInSubject.next(isLoggedIn);

    return isLoggedIn;
  }

  logout(){
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refresh_token");
    this.isLoggedInSubject.next(false);
    this.userNameSubject.next("")
    this.stopRefreshTokenInterval();
    this.route.navigate(['/login']);
  }

  startRefreshTokenInterval(){
    this.refreshTokenInterval = setInterval(() => {
    }, 100000);
  }

  stopRefreshTokenInterval(){
    clearInterval(this.refreshTokenInterval);
  }


}
