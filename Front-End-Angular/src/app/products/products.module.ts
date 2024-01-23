import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SharedModule } from '../shared/shared.module';
import { ListProductsComponent } from './components/list-products/list-products.component';
import { NavProductsComponent } from './components/nav-products/nav-products.component';
import { ProductsComponent } from './components/products/products.component';
import { ShowProductComponent } from './components/show-product/show-product.component';
import { ProductsRoutingModule } from './products-routing.module';


@NgModule({
  declarations: [
    ListProductsComponent,
    ProductsComponent,
    NavProductsComponent,
    ShowProductComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    SharedModule,
    MatProgressSpinnerModule,
    MatIconModule
  ],
  exports: [ ListProductsComponent, NavProductsComponent ]
})
export class ProductsModule { }
