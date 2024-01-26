import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { SharedModule } from '../shared/shared.module';
import { AddAdressComponent } from './components/add-adress/add-adress.component';
import { ShowAdressComponent } from './components/show-adress/show-adress.component';
import { TypeAdressIconPipe } from './pipes/type-adress-icon.pipe';


@NgModule({
  declarations: [
    TypeAdressIconPipe,
    AddAdressComponent,
    ShowAdressComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatMenuModule,
    MatIconModule,
    SharedModule,
    MatRadioModule
  ],
  exports: [AddAdressComponent, ShowAdressComponent]
})
export class AdressModule { }
