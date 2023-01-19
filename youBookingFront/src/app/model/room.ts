import {Hotel} from "./Hotel";
import Long from 'long';

export class Room {

  roomId !: Long;
  capacity !: number;
  name !: string;
  price !: number;
  numberOfRoom !: number;
  hotel !: Hotel;

}
