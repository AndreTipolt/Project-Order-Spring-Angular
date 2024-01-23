import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../types/Category.interface';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Product } from 'src/app/products/types/Product.interface';

@Component({
  selector: 'app-list-categories',
  templateUrl: './list-categories.component.html',
  styleUrls: ['./list-categories.component.scss']
})
export class ListCategoriesComponent implements OnInit {

  category!: Category;
  products!: Product[]

  constructor(private activatedRoute: ActivatedRoute,
              private categoryService: CategoryService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    const idCategory = this.activatedRoute.snapshot.params['id']; // Taking id category from url

    this.refreshCategory(idCategory);
  }

  refreshCategory(filterCategoryById: string) { // If there is a category filter

    this.categoryService.findProductsByCategoryId(filterCategoryById).subscribe({
      next: (res: Category) => {

        this.category = res;

        console.log(res.listProducts);

        this.products = res.listProducts;
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
