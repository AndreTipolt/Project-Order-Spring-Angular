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

  products: Product[] = [];
  categories!: Category[];

  size: number = 1;
  page: number = 0;
  finishSearchMoreItems: boolean = false

  constructor(private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Home")


    this.refreshProducts();
  }

  refreshProducts() {

    // if(this.finishSearchMoreItems) return;

    this.productService.getAllProducts(this.page, this.size).subscribe({
      next: (res) => {
        // this.products = res.content.map((product) => {

        //   let valueInstallent = product.fowardPrice / product.installments

        //   product.valueInstallment = valueInstallent
        //   return product;
        // });
        // if (res.content.length <= 0) {
        //   this.finishSearchMoreItems = true
        //   return;
        // }

        // res.forEach((value: Product) => {
        //   this.products.push(value)
        // });

        this.products = res
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


  onSearchMoreProducts() {

    // this.page++;
    // for(let i = 0; i < 10; i++ ){
    //   console.log()
    //   this.products.push(this.products[Math.floor(Math.random() * 4)])
    // }

    // this.refreshProducts()

    return;
  }
}
