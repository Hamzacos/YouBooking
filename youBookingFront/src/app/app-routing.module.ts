import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {HomeClientComponent} from "./component/home-client/home-client.component";
import {SingUpComponent} from "./component/sing-up/sing-up.component";
import {HotelComponent} from "./hotel/hotel.component";
import {ReservationComponent} from "./component/reservation/reservation.component";

const routes: Routes = [
  {path: '',redirectTo:'/login',pathMatch:'full'},
  {path:'login',component:LoginComponent},
  {path:'home-client',component:HomeClientComponent},
  {path:"sing-up",component:SingUpComponent},
  { path: 'hotel/:id', component: HotelComponent },
  { path: 'reservation', component: ReservationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
