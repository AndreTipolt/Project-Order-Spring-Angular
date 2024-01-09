import { HttpErrorResponse, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user/services/user.service';
import { GetImageAccount } from 'src/app/user/types/GetImageAccount.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;
  constructor(private userService: UserService) { }

  ngOnInit(): void {


    this.userService.getImageAccount().subscribe({

      error: (responseError: HttpErrorResponse) => {

        this.isLogged = false;
      },
      next: (response) => {

        this.isLogged = true;
      }
    })
  }

}
