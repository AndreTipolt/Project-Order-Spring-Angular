import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from '../shared/shared.module';
import { CartRoutingModule } from './cart-routing.module';
import { ShowCartComponent } from './components/show-cart/show-cart.component';

@NgModule({
  declarations: [
    ShowCartComponent
  ],
  imports: [
    CommonModule,
    CartRoutingModule,
    SharedModule,
    MatIconModule
  ]
})
export class CartModule { }
