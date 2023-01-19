import {Component, OnInit} from '@angular/core';
import {HotelService} from "../../service/hotel.service";
import {Hotel} from "../../model/Hotel";
import {Router} from "@angular/router";
import {LoginuserService} from "../../service/loginuser.service";
import {Subscription} from "rxjs";
import {HttpHeaders} from "@angular/common/http";



@Component({
  //providers: [HotelService],
  selector: 'app-home-client',
  templateUrl: './home-client.component.html',
  styleUrls: ['./home-client.component.css']
})
export class HomeClientComponent implements OnInit  {

  hotels !: Hotel[];
  hotelId !: number;
  isLoggedIn = false;
  user_name ="";
  private isLoggedInSubscription!: Subscription;
  private userName!: Subscription;
  constructor(private hotelService: HotelService,private route: Router ,private authService: LoginuserService,private router:Router) { }

  ngOnInit() {
    if (!this.authService.isLogedIn()) {
      this.router.navigate(['/login']);
    }
    this.isLoggedInSubscription = this.authService.isLoggedIn.subscribe(
      (isLoggedIn) => {
        this.isLoggedIn = isLoggedIn;
      }
    );
    this.userName = this.authService.userLogged.subscribe(
      (user) => {
        if(user) {
          if(user.length>0){
            this.user_name = user.toString();
            console.log(this.user_name)
          }
        }else {
          console.log("that's not work")
        }
      }
    );
    let headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'));
    this.hotelService.getHotelList({headers: headers}).subscribe(
      (response:Set<Hotel>)=>{
        this.hotels = Array.from(response);
      }
    )
  }

  selectHotel(id: number) {
    this.hotelService.setSelectedHotelId(id);
    this.route.navigate(['/hotel', id]);
  }

  logout() {
    this.authService.logout();
  }
}
