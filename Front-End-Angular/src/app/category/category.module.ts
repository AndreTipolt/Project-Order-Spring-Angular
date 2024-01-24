import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module';
import { ListCategoriesComponent } from './components/list-categories/list-categories.component';
import { ProductsModule } from '../products/products.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    ListCategoriesComponent
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    ProductsModule,
    SharedModule
  ]
})
export class CategoryModule { }
