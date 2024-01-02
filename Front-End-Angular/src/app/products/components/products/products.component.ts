import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../types/Category.interface';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products!: Product[];
  categories!: Category[];

  constructor(private productService: ProductService, private categoryService: CategoryService) { }

  ngOnInit(): void {

    this.refreshProducts();
    this.refreshCategories();
  }

  refreshProducts() {
    this.productService.getAllProducts().subscribe({
      next: (res) => {
        this.products = res.content;
      },
      error: (err) => {
        // Add message error
      }
    })
  }

  refreshCategories(){

    this.categoryService.getAllCategories().subscribe({
      next: (res) =>{
        console.log(res)
        this.categories = res;
      }
    })
  }

}
