import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { SpinnerLoadingComponent } from './components/spinner-loading/spinner-loading.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { SucessDialogComponent } from './components/sucess-dialog/sucess-dialog.component';

@NgModule({
  declarations: [
    ErrorDialogComponent,
    SpinnerLoadingComponent,
    ConfirmationDialogComponent,
    SucessDialogComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ],
  exports: [ErrorDialogComponent, SpinnerLoadingComponent, ConfirmationDialogComponent, SucessDialogComponent]
})
export class SharedModule { }
