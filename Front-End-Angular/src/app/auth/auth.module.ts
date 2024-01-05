import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatInputModule } from '@angular/material/input';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MatInputModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
