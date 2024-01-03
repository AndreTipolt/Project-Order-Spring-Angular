import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../types/Category.interface';
import { Product } from '../types/Product.interface';
import { Response } from '../types/Response.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient) { }

  getAllProducts(): Observable<Response> {

    const APIURL = `${this.baseAPIURL}/products`;

    return this.httpClient.get<Response>(APIURL).pipe(first());
  }
}
