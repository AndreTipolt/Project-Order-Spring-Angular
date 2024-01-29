import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CartService } from 'src/app/cart/services/cart.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserService } from 'src/app/user/services/user.service';
import { DataHeader } from 'src/app/user/types/DataHeader.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;

  numberOfProductsInCart!: number;

  dataHeader!: DataHeader;

  constructor(private userService: UserService,
    private dialog: MatDialog,
    private router: Router,
    private cartService: CartService,
    private authService: AuthService) { }

  ngOnInit(): void {

    this.userService.getDataHeader().subscribe({

      error: (responseError: HttpErrorResponse) => {

        this.isLogged = false;
      },
      next: (response: DataHeader) => {

        this.dataHeader = response;
        this.isLogged = true;
      }
    })

    this.numberOfProductsInCart = this.cartService.countNumberOfProductsInCart();
  }

  logout() {
    let dialog = this.openDialogConfirmation("Deseja realmente sair?")

    dialog.afterClosed().subscribe({
      next: (result) => {

        if (result) {
          this.authService.logout();
          window.location.reload()
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

}
