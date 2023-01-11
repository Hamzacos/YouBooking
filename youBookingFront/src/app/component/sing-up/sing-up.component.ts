import {Component, OnInit} from '@angular/core';
import {User} from "../../modal/user";
import {SignupuserService} from "../../service/signupuser-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent  implements OnInit {

  user: User= new User();

  constructor(private signupuserservice:SignupuserService, private router: Router) { }

  ngOnInit(): void {
  }

  onSignUp(fname : String,username : String,email : String,password : String){
    this.user.fullName=fname;
    this.user.username=username;
    this.user.email=email;
    this.user.password=password;
    this.signupuserservice.signupUser(this.user).subscribe(data=>{
      this.router.navigateByUrl('login');
    },error => alert("Re-fill the fields !"))
  }

}
