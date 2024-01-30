import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { isEmpty } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CartService } from 'src/app/cart/services/cart.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserService } from 'src/app/user/services/user.service';
import { DataHeader } from 'src/app/user/types/DataHeader.interface';
import { Notification } from 'src/app/user/types/Notification.interface';
import { NotificationUser } from 'src/app/user/types/NotificationUser.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;

  numberOfProductsInCart!: number;

  dataHeader!: DataHeader;

  numberOfNotificationsNotReaded: number = 0;

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

        this.calculateNumberOfNotificationsNotReaded()

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

  onShowNotifications(){

    this.numberOfNotificationsNotReaded = 0
    let listIdNotifications: string[] = [];

    this.dataHeader.listNotifications.map((notificationUser: NotificationUser) => {
      
      if (!notificationUser.read) listIdNotifications.push(notificationUser.notification.id);

      return;
    })

    if(listIdNotifications.length <= 0) return;

    this.userService.changeToReadNotifications(listIdNotifications).subscribe();

  }

  calculateNumberOfNotificationsNotReaded(){

    this.dataHeader.listNotifications.map((notificationUser: NotificationUser) => {

      if (!notificationUser.read) this.numberOfNotificationsNotReaded++;
      
    });
  
  }

}
