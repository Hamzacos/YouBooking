import { Component , OnInit} from '@angular/core';
import {User} from "../../model/user";
import {LoginuserService} from "../../service/loginuser.service";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {SignupuserService} from "../../service/signupuser-service.service";
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements  OnInit {

  //user: User= new User();
  errorMessage: String = "";
  successMessage: String = "";
  user: User;
  isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn = this.isLoggedInSubject.asObservable();
  userNameSubject = new BehaviorSubject<String>("");
  userLogged = this.userNameSubject.asObservable();
  role: String = "CLIENT";
  jwt!: any;

  constructor(private authService: LoginuserService, private router: Router, private jwtHelper: JwtHelperService) {
    this.user = new User();
  }


  ngOnInit(): void {
    if (this.authService.isLogedIn()) {
      this.router.navigate(['/home-client']);
    }
  }


  onSubmit() {
    console.log("first step")
    this.authService.signIn(this.user).subscribe(
      (response) => {
        console.log("second step")
        console.log(response)
        if (response instanceof HttpErrorResponse) {
          this.errorMessage = response.error.error;
          console.log(this.errorMessage)
        } else {
          this.errorMessage = "";
          this.successMessage = "you are connected";
          this.router.navigate(['/home-client']);
          setTimeout(() => {
            this.successMessage = '';
            this.router.navigate(['']);
          }, 2500);
        }
      },
      (error) => {
        this.errorMessage = error;
      }
    );
  }

  isLogedIn(): boolean {
    const token = localStorage.getItem('accessToken');
    if(token!=null){
      const user = this.jwtHelper.decodeToken(token).user_name
      this.userNameSubject.next(user)

    }
    const isLoggedIn = !this.jwtHelper.isTokenExpired(token);
    this.isLoggedInSubject.next(isLoggedIn);
    return isLoggedIn;
  }


}
