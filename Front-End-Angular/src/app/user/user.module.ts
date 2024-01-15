import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { ProfileComponent } from './components/profile/profile.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { InformationsUserComponent } from './components/informations-user/informations-user.component';
import { OrdersUserComponent } from './components/orders-user/orders-user.component';
import { StatusPaymentPipe } from './pipes/status-payment.pipe';
import { AdressesUserComponent } from './components/adresses-user/adresses-user.component';
import { MatMenuModule } from '@angular/material/menu';
import { TypeAdressIconPipe } from './pipes/type-adress-icon.pipe';


@NgModule({
  declarations: [
    ProfileComponent,
    InformationsUserComponent,
    OrdersUserComponent,
    StatusPaymentPipe,
    AdressesUserComponent,
    TypeAdressIconPipe
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatSidenavModule,
    MatIconModule,
    MatMenuModule
  ]
})
export class UserModule { }
