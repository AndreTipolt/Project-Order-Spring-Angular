import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserResponse } from 'src/app/user/types/UserResponse.interface';
import { AdressService } from '../../services/adress.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-show-adresses',
  templateUrl: './show-adress.component.html',
  styleUrls: ['./show-adress.component.scss']
})
export class ShowAdressComponent implements OnInit {

  @Input() currentUser!: UserResponse

  constructor(private title: Title,
              private dialog: MatDialog,
              private adressService: AdressService) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Meus Endereços")
  }

  deleteAdressById(adressId: string){
    const dialog = this.openDialogConfirmation("Deseja realmente excluir esse endereço?")


    dialog.afterClosed().subscribe({

      next: (result) => {
        if(result){

          this.adressService.deleteAdress(adressId).subscribe({
            next: () => {
              window.location.reload()
            },
            error: (error: HttpErrorResponse) => {
              this.openErrorDialog("Não foi possível excluir esse endereço");
            }
          });
        }
      }
    })

  }

  openDialogConfirmation(message: string) {

    const dialogReg = this.dialog.open(ConfirmationDialogComponent, {
      width: '300px',
      data: message,
      height: '140px'
    })

    return dialogReg;
  }

  openErrorDialog(message: string){

    return this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }

}
