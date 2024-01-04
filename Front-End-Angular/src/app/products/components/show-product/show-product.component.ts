import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../types/Product.interface';

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
              private productService: ProductService) { }

  ngOnInit(): void {

    const idProduct: string = this.activatedRoute.snapshot.params['id'];

    this.productService.getProductById(idProduct).subscribe({

      next: (res) => {
        this.product = res
        this.title.setTitle(res.name)
      },
      error: (err) => {
        this.router.navigate(['/'])
      }
    })
    
  }

}
