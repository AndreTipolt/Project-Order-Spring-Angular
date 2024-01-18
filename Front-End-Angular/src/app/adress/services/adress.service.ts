import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { DataCepResponse } from '../types/DataCepResponse.interface';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { SaveAdress } from '../types/SaveAdress.interface';

@Injectable({
  providedIn: 'root'
})
export class AdressService {

  private readonly baseApiURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient,
    private authService: AuthService) { }


  getInformationsAdress(cep: string) {

    const apiURL = `https://viacep.com.br/ws/${cep}/json/`;

    return this.httpClient.get<DataCepResponse>(apiURL);
  }

  saveAdress(dataForm: SaveAdress){

    let headers = new HttpHeaders()

    headers = headers.append("Authorization", this.authService.getAcessToken())

    const apiURL = `${this.baseApiURL}/adresses`

    return this.httpClient.post(apiURL, dataForm, { headers });

  }
}
