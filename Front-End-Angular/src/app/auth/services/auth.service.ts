import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { environment } from 'src/environments/environment';
import { Login } from '../types/Login.interface';
import { RegisterUser } from '../types/RegisterUser.interface';
import { TokenSucessLogin } from '../types/TokenSucessLogin.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient,
    private cookieService: CookieService) { }

  login(dataForm: Login) {

    const apiURL = `${this.baseAPIURL}/auth/login`;

    return this.httpClient.post<TokenSucessLogin>(apiURL, dataForm);
  }

  register(dataForm: RegisterUser) {

    const apiURL = `${this.baseAPIURL}/user/save`;

    return this.httpClient.post(apiURL, dataForm);
  }

  getAcessToken(): string {

    return this.cookieService.get("acess_token")
  }

  logout() {

    this.cookieService.delete("acess_token")

    if (this.cookieService.check("acess_token")) {
      this.cookieService.delete("acess_token")
    }
    return;
  }

}
