import { CartService } from 'src/app/cart/services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Product } from 'src/app/products/types/Product.interface';

@Component({
  selector: 'app-show-cart',
  templateUrl: './show-cart.component.html',
  styleUrls: ['./show-cart.component.scss']
})
export class ShowCartComponent implements OnInit {

  product!: Product

  constructor(private title: Title,
              private cartService: CartService) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Carrinho')

    this.product = this.cartService.getAllProductsInCart()

  }

}
