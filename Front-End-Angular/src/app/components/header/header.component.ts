import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CartService } from 'src/app/cart/services/cart.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;

  numberOfProductsInCart!: number;

  constructor(private userService: UserService,
    private dialog: MatDialog,
    private router: Router,
    private cartService: CartService) { }

  ngOnInit(): void {

    this.userService.getImageAccount().subscribe({

      error: (responseError: HttpErrorResponse) => {

        this.isLogged = false;
      },
      next: (response) => {

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
          this.router.navigate(["/auth/logout"])
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
