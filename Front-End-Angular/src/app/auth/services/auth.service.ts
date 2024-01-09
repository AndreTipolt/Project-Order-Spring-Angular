import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../types/Login.interface';
import { environment } from 'src/environments/environment';
import { TokenSucessLogin } from '../types/TokenSucessLogin.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient) { }

  login(dataForm: Login){

    const apiURL = `${this.baseAPIURL}/auth/login`;

    return this.httpClient.post<TokenSucessLogin>(apiURL, dataForm);
  }
}
