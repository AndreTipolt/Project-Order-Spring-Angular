import { Component, OnInit } from '@angular/core';
import { Observable, filter } from 'rxjs';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../types/Category.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  products!: Product[];
  categories!: Category[];

  constructor(private productService: ProductService, 
    private categoryService: CategoryService, 
    private activatedRoute: ActivatedRoute, 
    private router: Router,
    private dialog: MatDialog,
    private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Home")
    
    if (this.router.url.startsWith('/category')) { // Verify if the url contains category for list products using category

      const idCategory = this.activatedRoute.snapshot.params['id']; // Taking id category from url

      this.refreshCategories(idCategory);

    }
    else{
      this.refreshCategories();
      this.refreshProducts();
    }
  }

  refreshProducts() {
    this.productService.getAllProducts().subscribe({
      next: (res) => {
        this.products = res.content;
      },
      error: (err) => {
        this.onError("Erro ao tentar carregar produtos")
      }
    })
  }

  refreshCategories(filterCategoryById = null) {

    this.categoryService.getAllCategories().subscribe({
      next: (res) => {

        if(filterCategoryById != null){
          res.map((category) => {
            if(category.id == filterCategoryById){
              this.products = category.listProducts
            }
          });
        }
        this.categories = res;
      },
      error: (error) => {
        this.onError("Erro ao tentar carregar as categorias")
      }
    })
  }

  onError(errorMessage: string){

    this.dialog.open(ErrorDialogComponent, {
      data: errorMessage
    })
  }

}
