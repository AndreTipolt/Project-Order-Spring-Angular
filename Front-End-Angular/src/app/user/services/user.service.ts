import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { environment } from 'src/environments/environment';
import { UserResponse } from '../types/UserResponse.interface';
import { DataHeader } from '../types/DataHeader.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient,
    private authService: AuthService) { }

  getDataHeader() {

    const apiURL = `${this.baseAPIURL}/user/header`

    let headers: HttpHeaders = this.createHeaderWithAcessToken();

    return this.httpClient.get<DataHeader>(apiURL, { headers })

  }

  createHeaderWithAcessToken(): HttpHeaders {

    let headers = new HttpHeaders()

    headers = headers.append("Authorization", this.authService.getAcessToken())

    return headers;
  }

  getUserData(){

    const apiURL = `${this.baseAPIURL}/user`

    let headers = new HttpHeaders()

    headers = headers.append("Authorization", this.authService.getAcessToken())

    return this.httpClient.get<UserResponse>(apiURL, { headers })
  }
}
