import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  Router,
  RouterStateSnapshot,
  UrlSegment
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { ShowComponentUser } from '../types/ShowComponentUser.interface';

@Injectable({
  providedIn: 'root'
})
export class RoutingResolver implements Resolve<ShowComponentUser> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ShowComponentUser> {

    const uri = state.url

    if(uri === "/my-profile"){

      return of({ showInformationsUserComponent: true })
    } 

    else if(uri === "/my-profile/my-orders"){

      return of({ showOrdersUserComponent: true })

    }
    else if(uri === "/my-profile/my-adresses"){
      return of({ showAdressesUserComponent: true })
    }

    else if(uri === "/my-profile/my-adresses/add"){
      return of({ showAddAdressesUserComponent: true })
    }

    return of({ showInformationsUserComponent: true })
  }
}
