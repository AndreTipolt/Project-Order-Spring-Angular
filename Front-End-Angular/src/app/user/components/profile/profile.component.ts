import { Component, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { UserService } from '../../services/user.service';
import { UserResponse } from '../../types/UserResponse.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { InformationsUserComponent } from '../informations-user/informations-user.component';
import { OrdersUserComponent } from '../orders-user/orders-user.component';
import { ShowComponentUser } from '../../types/ShowComponentUser.interface';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser!: UserResponse

  componentConfig!: ShowComponentUser

  constructor(private title: Title,
              private userService: UserService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.title.setTitle('Spring - Meu Perfil')

    this.componentConfig = this.activatedRoute.snapshot.data['routing']

    this.userService.getUserData().subscribe({
      error: (error: HttpErrorResponse) => {

        this.router.navigate(['/auth/login']);
      },
      next: (res) => {
        this.currentUser = res;

        console.log(res)
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
    else if(component === "/my-profile/my-adresses"){
      return this.componentConfig.showAdressesUserComponent;
    }
    else if(component === "/my-profile/my-adresses/add"){
      return this.componentConfig.showAddAdressesUserComponent;
    }
    else if(component === "/my-profile/my-informations"){
      return this.componentConfig.showMyInformationsUserComponent;
    }

    return false;
  }

}
