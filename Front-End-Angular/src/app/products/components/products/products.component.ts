import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Category } from '../../../category/types/Category.interface';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  products!: Product[];
  categories!: Category[];

  constructor(private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Home")

    if (this.router.url.startsWith('/category')) { // Verify if the url contains category for list products using category

      const idCategory = this.activatedRoute.snapshot.params['id']; // Taking id category from url

    }
    else {
      this.refreshProducts();
    }
  }

  refreshProducts() {
    this.productService.getAllProducts().subscribe({
      next: (res) => {
        // this.products = res.content.map((product) => {

        //   let valueInstallent = product.fowardPrice / product.installments

        //   product.valueInstallment = valueInstallent
        //   return product;
        // });
        this.products = res.content;
      },
      error: (err) => {
        this.onError("Erro ao tentar carregar produtos")
      }
    })
  }

  onError(errorMessage: string) {

    this.dialog.open(ErrorDialogComponent, {
      data: errorMessage
    })
  }


  getDiameterSpinnerLoading(): number {
    return 100;
  }

}
