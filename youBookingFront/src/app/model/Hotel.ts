import {Room} from "./room";

export class Hotel {
  id_hotel  !: number;
  name      !: String;
  city      !: String;
  address   !: String;
  approved  !: boolean;
  rooms !: Room[];
}
