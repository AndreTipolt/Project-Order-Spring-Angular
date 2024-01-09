import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProductsComponent } from './components/list-products/list-products.component';
import { ProductsComponent } from './components/products/products.component';
import { ShowProductComponent } from './components/show-product/show-product.component';

const routes: Routes = [
  { path: '', component: ProductsComponent },
  { path: 'category/:id', component: ProductsComponent },
  { path: 'products/:id', component: ShowProductComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
