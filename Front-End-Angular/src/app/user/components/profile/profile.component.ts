import { Component, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { UserService } from '../../services/user.service';
import { UserResponse } from '../../types/UserResponse.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { InformationsUserComponent } from '../informations-user/informations-user.component';
import { OrdersUserComponent } from '../orders-user/orders-user.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser!: UserResponse

  componentConfig = {
    showInformationsUserComponent: false,
    showOrdersUserComponent : false
  }
  
  constructor(private title: Title,
              private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.title.setTitle('Spring - Meu Perfil')

    const uri = this.router.url;

    if(uri === "/my-profile"){
      this.componentConfig.showInformationsUserComponent = true
    } 

    else if(uri === "/my-profile/my-orders"){
      this.componentConfig.showOrdersUserComponent= true
    }

    this.userService.getUserData().subscribe({
      error: (error: HttpErrorResponse) => {

        this.router.navigate(['/auth/login']);
      },
      next: (res) => {
        console.log(res.listOrders)
        this.currentUser = res;
      }
    })
  }

  showComponent(component: string){

    if(component === "/my-profile"){
      return this.componentConfig.showInformationsUserComponent;
    }

    else if(component === "/my-profile/my-orders"){
      return this.componentConfig.showOrdersUserComponent;
    }

    return false;
  }

}
