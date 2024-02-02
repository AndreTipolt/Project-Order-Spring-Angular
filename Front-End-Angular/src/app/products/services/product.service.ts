import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../../category/types/Category.interface';
import { Product } from '../types/Product.interface';
import { Response } from '../types/Response.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient) { }

  getAllProducts(page: number, size: number): Observable<Product[]> {

    const APIURL = `${this.baseAPIURL}/products`;

    return this.httpClient.get<Product[]>(APIURL).pipe(first());
  }
  // getAllProducts(page: number, size: number): Observable<Response> {

  //   const APIURL = `${this.baseAPIURL}/products?size=${size}&page=${page}`;

  //   return this.httpClient.get<Response>(APIURL).pipe(first());
  // }

  getProductById(productId: string): Observable<Product> {

    const APIURL = `${this.baseAPIURL}/products/${productId}`;

    return this.httpClient.get<Product>(APIURL);
  }

  searchProductByName(productName: string) {

    const APIURL = `${this.baseAPIURL}/products/search?q=${productName}`;

    return this.httpClient.get<Product[]>(APIURL);
  }
}
