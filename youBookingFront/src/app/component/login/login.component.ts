import { Component , OnInit} from '@angular/core';
import {User} from "../../modal/user";
import {LoginuserService} from "../../service/loginuser.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements  OnInit {

  user: User= new User();


  constructor(private loginuserservice:LoginuserService, private router: Router) { }

  ngOnInit(): void {
  }
  // noinspection JSDeprecatedSymbols
  onSignIn(user : String , pass : String) {

    this.user.username = user ;
    this.user.password=pass;

    this.loginuserservice.loginUser(this.user).subscribe(/*data=>{
      //alert("Login Success!")
      //this.router.navigateByUrl('home-client');
    },*/
      token => {
        this.router.navigate(['/home-client', token]);
      },
        error => alert("error login")
    );
  }
}
