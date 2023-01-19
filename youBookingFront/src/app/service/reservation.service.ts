import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {Reservation} from "../model/reservation";
import {catchError, Observable, of, throwError} from "rxjs";
import {Room} from "../model/room";
import {Hotel} from "../model/Hotel";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private reservationApiUrl = 'http://localhost:8081/addReservation';
  private baseUrl = 'http://localhost:8081';
  private error: any;
  room !: Room;
  reservation !: Reservation;

  constructor(private http: HttpClient) {}

  createReservation(reservation : any,options: {headers: HttpHeaders}) {
    return this.http.post(this.reservationApiUrl, reservation,options).pipe(
      catchError(this.handleError)
    );
  }
  handleError(error: HttpErrorResponse) {
    console.log(error);
    return of(error);
  }

  getHotelReservationList(options: {headers: HttpHeaders}): Observable<any> {
    return this.http.get<Reservation>(`${this.baseUrl}/my-reservations`,options);
  }

  getRoom(): Room {
    return this.room;
  }

  cancelReservation(id: number){
    if (!id) {
      throw new Error('id parameter is required');
    }
    return this.http.put(`${this.baseUrl}/cancel/${id}`,{}).subscribe(
      data => {
        console.log('Reservation cancelled successfully', data);
        //this.reservation.find(reserv => reserv.reservationId === id).status = 'Annulé';
      },
      error => {
        console.log('Error cancelling reservation', error);
      }
    );
  }

  updateReservation(id: number, startDate: string , endDate: string ) {
    const body = { startDate: startDate, endDate: endDate };
    const params = new HttpParams().set('startDate', startDate).set('endDate', endDate);
    return this.http.put(`${this.baseUrl}/updateReservation/${id}`, null, { params });
  }

}

