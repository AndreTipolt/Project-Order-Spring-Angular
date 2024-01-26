import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';
import { CartService } from 'src/app/cart/services/cart.service';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.scss']
})
export class ShowProductComponent implements OnInit {

  product!: Product;

  constructor(private title: Title,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private productService: ProductService,
              private cartService: CartService) { }

  ngOnInit(): void {

    const idProduct: string = this.activatedRoute.snapshot.params['id'];

    this.productService.getProductById(idProduct).subscribe({

      next: (res) => {
        res.valueInstallment = res.fowardPrice / res.installments
        this.product = res
        this.title.setTitle(res.name)
      },
      error: (err) => {
        this.router.navigate(['/'])
      }
    })

  }

  addCart(){

    if(this.product.id === undefined) return;

    this.cartService.addProductInCart(this.product.id);
  }

}
