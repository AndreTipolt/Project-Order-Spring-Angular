import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SharedModule } from '../shared/shared.module';
import { ListProductsComponent } from './components/list-products/list-products.component';
import { NavProductsComponent } from './components/nav-products/nav-products.component';
import { ProductsComponent } from './components/products/products.component';
import { ProductsRoutingModule } from './products-routing.module';
import { SpinnerLoadingComponent } from './components/spinner-loading/spinner-loading.component';


@NgModule({
  declarations: [
    ListProductsComponent,
    ProductsComponent,
    NavProductsComponent,
    SpinnerLoadingComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    SharedModule,
    MatProgressSpinnerModule,
  ]
})
export class ProductsModule { }