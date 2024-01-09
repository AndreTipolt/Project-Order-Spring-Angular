import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../types/Login.interface';
import { environment } from 'src/environments/environment';
import { TokenSucessLogin } from '../types/TokenSucessLogin.interface';
import { RegisterUser } from '../types/RegisterUser.interface';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient,
              private cookieService: CookieService) { }

  login(dataForm: Login){

    const apiURL = `${this.baseAPIURL}/auth/login`;

    return this.httpClient.post<TokenSucessLogin>(apiURL, dataForm);
  }

  register(dataForm: RegisterUser){

    const apiURL = `${this.baseAPIURL}/users/save`;

    return this.httpClient.post(apiURL, dataForm);
  }

}
