import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShowCartComponent } from './components/show-cart/show-cart.component';
import { AuthResolver } from '../auth/guards/auth.resolver';

const routes: Routes = [
  { path: '', component: ShowCartComponent, resolve:[AuthResolver] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
