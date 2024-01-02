import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products$!: Observable<Product[]>
  products!: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {

    this.refreshProducts()
  }

  refreshProducts() {
    this.productService.getAllProducts().subscribe({
      next: (res) => {
        this.products = res.content
      },
      error: (err) => {
        // Add message error
      }
    })
  }

}
