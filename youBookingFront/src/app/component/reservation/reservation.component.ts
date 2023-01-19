import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ReservationService} from "../../service/reservation.service";
import {Hotel} from "../../model/Hotel";
import {Reservation} from "../../model/reservation";
import {Room} from "../../model/room";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DatePipe} from "@angular/common";
import moment from 'moment';
import {HttpHeaders} from "@angular/common/http";
import {LoginuserService} from "../../service/loginuser.service";

@Component({
  selector: 'app-reservation',
  templateUrl:'./reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  reservation: any;
  room !: Room;
  reservationId !: number;
  selectedReservation !: Reservation ;
  pageOfItems !: Array<any>;
  @ViewChild('updateModal') updateModal !: TemplateRef<any>;
  selectedStartDate!: Date;
  selectedEndDate !: Date;
  constructor(private reservationService: ReservationService, private modalService: NgbModal,private datePipe: DatePipe,private authService: LoginuserService) {
  }

  pages !: number[];
  itemsToDisplay: Array<{ id_reservation: number; totalPrice: number; startDate: string; endDate: string; status: string; room: { name: string; }; }> = []

  ngOnInit() {
    let headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'));
    this.reservationService.getHotelReservationList({headers: headers}).subscribe((reservation: Array<{ id_reservation: number; totalPrice: number; startDate: string; endDate: string; status: string; room: { name: string } }>) => {
      this.reservation = reservation;
      this.pages = Array.from({length: this.totalPages}, (_, i) => i + 1);
      console.log(reservation)
      this.goToPage(1);
    });

  }

  itemsPerPage = 1;
  currentPage = 1;

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateItemsToDisplay();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updateItemsToDisplay();
    }
  }

  private updateItemsToDisplay() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.itemsToDisplay = this.reservation.slice(startIndex, endIndex);
  }

  get totalPages() {
    return Math.ceil(this.reservation.length / this.itemsPerPage);
  }


  goToPage(page: number) {
    this.currentPage = page;
    this.updateItemsToDisplay();
  }


  cancelReserve(id_reservation: number) {
    this.reservationId = id_reservation;
    console.log(this.reservationId);
    this.reservationService.cancelReservation(this.reservationId);
    alert('Vous avez annulez votre Reservation')
  }

  openUpdateModal(reserv: { id_reservation: any; totalPrice: any; startDate: any; endDate: any; status: any; room: { name: any; }; }) {
    const reservation = Object.assign(reserv);
    this.modalService.open(this.updateModal, {size: 'lg', centered: true});
    this.selectedReservation = reservation;
    console.log(this.selectedReservation);
  }


  updateReservation(id: number, startDate: Date, endDate: Date) {
    let str = moment(startDate, "YYYY-MM-DD", true);
    let startString = str.format("YYYY-MM-DD");
    let end = moment(endDate, "YYYY-MM-DD", true);
    let endString = end.format("YYYY-MM-DD");
    this.reservationService.updateReservation(id, startString, endString).subscribe(() => {
      this.modalService.dismissAll();
    });
  }

  logout() {
    this.authService.logout();
  }
}
