import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-search-products',
  templateUrl: './search-products.component.html',
  styleUrls: ['./search-products.component.scss']
})
export class SearchProductsComponent implements OnInit {

  products!: Product[];
  searchedNameProduct!: string;

  constructor(private title: Title,
    private productService: ProductService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(param => {

      this.searchedNameProduct = param['q']

    })

    this.title.setTitle('Spring - Busca')

    this.searchProductByName(this.searchedNameProduct);
  }


  searchProductByName(productName: string){

    this.productService.searchProductByName(productName).subscribe({
      error: (error: HttpErrorResponse) => {
      },
      next: (res: Product[]) => {

        this.products = res

      }
    })
  }


}
