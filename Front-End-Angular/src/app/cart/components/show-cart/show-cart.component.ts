import { CartService } from 'src/app/cart/services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Product } from 'src/app/products/types/Product.interface';
import { ProductService } from 'src/app/products/services/product.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MenuSubTotalData } from '../../types/MenuSubTotalData.interface';

@Component({
  selector: 'app-show-cart',
  templateUrl: './show-cart.component.html',
  styleUrls: ['./show-cart.component.scss']
})
export class ShowCartComponent implements OnInit {

  products: Product[] = []

  menuSubTotalData!: MenuSubTotalData;

  constructor(private title: Title,
              private cartService: CartService,
              private productService: ProductService) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Carrinho');

    const listIdProducts = this.cartService.getAllProductsInCart();

    this.findProductsToShowInCart(listIdProducts);

    this.calculateResumeMenuValues()
  }

  findProductsToShowInCart(listIdProducts: string[]){

    listIdProducts.map(async (productId) => {

      await this.productService.getProductById(productId).subscribe({

        next: (res) => {

          this.products.push(res);
        },

        error: (error: HttpErrorResponse) => {
          console.log(error);
        }

      })

    })
  }

  calculateResumeMenuValues(){

    this.menuSubTotalData.numberOfProducts = this.products.length;

    this.menuSubTotalData.valueDelivery = 0;

    this.products.map((product) => {

      this.menuSubTotalData.valueTotalOfProducts += product.spotPrice
    })

    this.menuSubTotalData.totalOrder = this.menuSubTotalData.valueDelivery + this.menuSubTotalData.valueTotalOfProducts

  }
}
