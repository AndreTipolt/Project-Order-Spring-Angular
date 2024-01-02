import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products$!: Observable<Product[]>
  
  constructor() { }

  ngOnInit(): void {
  }

}
