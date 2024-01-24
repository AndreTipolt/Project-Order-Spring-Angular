import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { ShowProductComponent } from './components/show-product/show-product.component';
import { SearchProductsComponent } from './components/search-products/search-products.component';

const routes: Routes = [
  { path: '', component: ProductsComponent },
  { path: 'products/search', component: SearchProductsComponent },
  { path: 'products/:id', component: ShowProductComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
