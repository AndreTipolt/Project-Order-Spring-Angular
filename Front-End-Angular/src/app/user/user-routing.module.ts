import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthResolver } from '../auth/guards/auth.resolver';
import { RoutingResolver } from './resolver/routing.resolver';

const routes: Routes = [
  { path: '', component: ProfileComponent, resolve: { user: AuthResolver, routing: RoutingResolver } },
  { path: 'my-orders', component: ProfileComponent, resolve: { user: AuthResolver, routing: RoutingResolver } },
  { path: 'my-adresses', component: ProfileComponent, resolve: { user: AuthResolver, routing: RoutingResolver } },
  { path: 'my-adresses/add', component: ProfileComponent, resolve: { user: AuthResolver, routing: RoutingResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
