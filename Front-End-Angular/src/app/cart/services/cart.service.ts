import { Product } from './../../products/types/Product.interface';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httClient: HttpClient) { }


  addProductInCart(product: Product){

    const cart_products = localStorage.getItem('cart_products')

    if(cart_products === null){
      localStorage.setItem('cart_products', JSON.stringify(product))
    }

  }
}
