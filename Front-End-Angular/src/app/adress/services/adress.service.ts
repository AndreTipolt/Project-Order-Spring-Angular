import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DataCepResponse } from '../types/DataCepResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class AdressService {

  constructor(private httpClient: HttpClient) { }


  getInformationsAdress(cep: string){

    const apiURL = `https://viacep.com.br/ws/${cep}/json/`;

    return this.httpClient.get<DataCepResponse>(apiURL);
  }
}
