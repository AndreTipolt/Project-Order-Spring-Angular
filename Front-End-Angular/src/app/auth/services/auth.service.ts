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
  private readonly client_id = environment.client_id;
  private readonly client_secret = environment.client_secret;

  constructor(private httpClient: HttpClient) { }

  login(dataForm: Login){

    const apiURL = `${this.baseAPIURL}/oauth/token`;

    const encodedCredentials = btoa(`${this.client_id}:${this.client_secret}`)

    let headers = new HttpHeaders()
        .append("Authorization", `Basic ${encodedCredentials}`)

    return this.httpClient.post<TokenSucessLogin>(apiURL, { dataForm,grant_type: "password" },{ headers });
  }
}
