import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AddAdressComponent } from '../adress/components/add-adress/add-adress.component';
import { ShowAdressComponent } from '../adress/components/show-adress/show-adress.component';
import { TypeAdressIconPipe } from '../adress/pipes/type-adress-icon.pipe';
import { InformationsUserComponent } from './components/informations-user/informations-user.component';
import { OrdersUserComponent } from './components/orders-user/orders-user.component';
import { ProfileComponent } from './components/profile/profile.component';
import { StatusPaymentPipe } from './pipes/status-payment.pipe';
import { UserRoutingModule } from './user-routing.module';


@NgModule({
  declarations: [
    ProfileComponent,
    InformationsUserComponent,
    OrdersUserComponent,
    StatusPaymentPipe,
    ShowAdressComponent,
    AddAdressComponent,
    TypeAdressIconPipe
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatSidenavModule,
    MatIconModule,
    MatMenuModule,
    MatInputModule,
    ReactiveFormsModule,
  ]
})
export class UserModule { }
