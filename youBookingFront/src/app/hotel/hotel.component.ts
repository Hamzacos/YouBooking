import {Component, Inject, TemplateRef, ViewChild} from '@angular/core';
import {HotelService} from "../service/hotel.service";
import {Hotel} from "../model/Hotel";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ReservationService} from "../service/reservation.service";
import {Reservation} from "../model/reservation";
import {Router} from "@angular/router";
import {Room} from "../model/room";
import {FormControl, FormGroup} from "@angular/forms";
import { DatePipe } from '@angular/common';
import {HttpHeaders} from "@angular/common/http";
import {LoginuserService} from "../service/loginuser.service";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css'],
})
export class HotelComponent {

  hotel: any;
  idHotel !:number;
  selectedRoomId : Room = new Room();
  priceRoom : Room = new Room();
  @ViewChild('content') content !: TemplateRef<any>;
  reservation !: Reservation;
  private error: any;
  private userName!: Subscription;
   user_name = "";

  constructor(private hotelService:HotelService,private modalService: NgbModal,private authService: LoginuserService
              ,private reservationService: ReservationService
              ,private router : Router,private datePipe: DatePipe) {}


  reservationForm !: FormGroup;

  ngOnInit(): void{
    if (!this.authService.isLogedIn()) {
      this.router.navigate(['/login']);
    }
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
    this.idHotel = this.hotelService.getSelectedHotelId();
    console.log(typeof this.idHotel);
    if(this.idHotel !== null && typeof this.idHotel === 'number'){
      let headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'));
      this.hotelService.getHotel(this.idHotel,{headers: headers}).
        subscribe((hotel:Hotel) => {
        this.hotel = hotel;
      });
    }else{
      console.log("The id passed is not a number")
    }

    this.reservationForm = new FormGroup({
      'startDate': new FormControl(''),
      'endDate': new FormControl(''),
    });
  }

    onBookNow(roomId: Room,price : Room) {
      this.selectedRoomId = roomId;
      this.priceRoom = price;
      this.modalService.open(this.content);
      console.log(this.selectedRoomId);
    }

  form = {
    startDate: '',
    endDate: ''
  };


  makeReservation() {
    if (this.form.startDate && this.form.endDate) {
      const startDate  = this.datePipe.transform(this.form.startDate, 'yyyy-MM-dd');
      const endDate = this.datePipe.transform(this.form.endDate, 'yyyy-MM-dd');
      const fullNam = this.user_name;
      const reservation1 = {
        "startDate": startDate,
        "endDate": endDate,
        "client": {
          "fullName": fullNam
        },
        "room": {
          "roomId": this.selectedRoomId,
          "price" : this.priceRoom
        }
      }
      let headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'));
      this.reservationService.createReservation(reservation1,{headers: headers}).subscribe(response => {
          console.log(response);
          this.modalService.dismissAll();
          this.router.navigateByUrl('/reservation')
        },
        (error: any)=>{
          console.log(error);
        });

    } else {
      console.log("Les champs de date sont obligatoires.");
    }
  }
}

