import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { HomeClientComponent } from './component/home-client/home-client.component';
import { SingUpComponent } from './component/sing-up/sing-up.component';
import { HotelComponent } from './hotel/hotel.component';
import { ReservationComponent } from './component/reservation/reservation.component';
import {NgbModalModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { MomentModule } from 'ngx-moment';
import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';
import { JwPaginationModule } from 'jw-angular-pagination';
import {JWT_OPTIONS, JwtModule} from "@auth0/angular-jwt";
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeClientComponent,
    SingUpComponent,
    HotelComponent,
    ReservationComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        NgbModalModule,
        HttpClientModule,
        NgbModule,
        MomentModule,
        ReactiveFormsModule,
        CommonModule,
        JwPaginationModule,
      JwtModule.forRoot({
        jwtOptionsProvider: {
          provide: JWT_OPTIONS,
          useValue: {
            tokenGetter: () => {
              return localStorage.getItem('access_token');
            },
          }
        }
      })
    ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
