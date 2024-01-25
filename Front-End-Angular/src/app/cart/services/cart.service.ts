import { Product } from './../../products/types/Product.interface';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httClient: HttpClient) { }


  addProductInCart(productId: string) {

    const cart_products = localStorage.getItem('cart_products')

    if (cart_products === null) return localStorage.setItem('cart_products', productId);

    localStorage.setItem('cart_products', cart_products + "/" + productId)
    return;
  }

  getAllProductsInCart(): string[] {

    const itemsCartInLocalStorage = localStorage.getItem('cart_products')

    if (itemsCartInLocalStorage === null) {

      return [];
    }

    return itemsCartInLocalStorage.split('/');
  }

  deleteItemInCart(productId: string){

    const allItensCart = localStorage.getItem('cart_products')

    // allItensCart?.split('/').filter((product))
  }
}
