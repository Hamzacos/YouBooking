import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../model/Hotel";


@Injectable({
  providedIn: 'root'
})
export class HotelService implements OnInit{

  hotel!:Hotel;

  private baseUrl = 'http://localhost:8081/hotleslist';

  private apiUrl = 'http://localhost:8081/hotel';

   private selectedHotelId !: number;


  constructor(private http:HttpClient) { }


  getHotelList(options: {headers: HttpHeaders}): Observable<Set<Hotel>> {
    return this.http.get<Set<Hotel>>(this.baseUrl, options);
  }

  getHotel(id: number, option: {headers: HttpHeaders}): Observable<Hotel> {
    if (!id) {
      throw new Error('id parameter is required');
    }
    return this.http.get<Hotel>(`${this.apiUrl}/${id}`,option);
  }


  setSelectedHotelId(id: number) {
    this.selectedHotelId = id;
  }

  ngOnInit(): void {
  }

  getSelectedHotelId() {
    return this.selectedHotelId;
  }
}
