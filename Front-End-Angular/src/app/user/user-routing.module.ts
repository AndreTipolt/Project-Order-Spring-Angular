import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthResolver } from '../auth/guards/auth.resolver';

const routes: Routes = [
  {path: '', component: ProfileComponent, resolve: { user: AuthResolver }}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
