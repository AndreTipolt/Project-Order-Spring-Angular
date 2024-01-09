import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.scss']
})
export class ListProductsComponent implements OnInit {

  @Input() products!: Product[];
  constructor() { }

  ngOnInit(): void {
  }

}
