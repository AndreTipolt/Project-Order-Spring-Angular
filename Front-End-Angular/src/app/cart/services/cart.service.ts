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

    return itemsCartInLocalStorage.split('/').filter((productId) => { return productId != "" });
  }

  deleteItemInCart(productId: string) {

    const allItensCart = localStorage.getItem('cart_products')

    if (allItensCart === null) {
      return;
    }

    const newCartItens = allItensCart?.split('/').filter((productIdLocalStorage) => { return productId != productIdLocalStorage && productIdLocalStorage != "" }).join('/').toString()


    localStorage.setItem('cart_products', newCartItens);

  }

  countNumberOfProductsInCart(): number {

    let cartProducts = localStorage.getItem('cart_products')?.split('/')

    if (cartProducts === undefined) {
      return 0;
    }

    return cartProducts.filter((productId) => productId != "").length
  }
}
