import * as moment from "moment";
import {Room} from "./room";
import {User} from "./user";


export class Reservation {

  id_reservation !: number;
  totalPrice !: number;
  startDate !: Date;
  endDate !: Date;
  status !: string;
  room !: Room;
  client !: User;



 /* constructor(id_reservation: number,totalPrice: number, startDate: Date, endDate: Date, status: string, room: Room) {
    this.id_reservation = totalPrice;
    this.totalPrice = totalPrice;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.check_in = moment(this.startDate).toDate();
    this.check_out = moment(this.endDate).toDate();
    this.room = room;
  }*/

  //public Reservation() {}

  getRoom() {
    return this.room;
  }
}
