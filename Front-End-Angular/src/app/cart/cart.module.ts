import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from '../shared/shared.module';
import { CartRoutingModule } from './cart-routing.module';
import { ShowCartComponent } from './components/show-cart/show-cart.component';
import { MenuSubTotalCartComponent } from './components/menu-sub-total-cart/menu-sub-total-cart.component';
import { ShowItemCartComponent } from './components/show-item-cart/show-item-cart.component';

@NgModule({
  declarations: [
    ShowCartComponent,
    MenuSubTotalCartComponent,
    ShowItemCartComponent
  ],
  imports: [
    CommonModule,
    CartRoutingModule,
    SharedModule,
    MatIconModule
  ]
})
export class CartModule { }
