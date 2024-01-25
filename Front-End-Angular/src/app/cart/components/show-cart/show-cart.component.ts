import { CartService } from 'src/app/cart/services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Product } from 'src/app/products/types/Product.interface';
import { ProductService } from 'src/app/products/services/product.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-show-cart',
  templateUrl: './show-cart.component.html',
  styleUrls: ['./show-cart.component.scss']
})
export class ShowCartComponent implements OnInit {

  products: Product[] = []

  constructor(private title: Title,
              private cartService: CartService,
              private productService: ProductService) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Carrinho')

    const listIdProducts = this.cartService.getAllProductsInCart()

    listIdProducts.map((productId) => {

      this.productService.getProductById(productId).subscribe({

        next: (res) => {
          console.log(this.products)
          this.products.push(res)
        },

        error: (error: HttpErrorResponse) => {
          console.log(error)
        }

      })
    })

  }

}
