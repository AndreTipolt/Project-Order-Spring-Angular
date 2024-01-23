import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from '../../../category/types/Category.interface';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-nav-products',
  templateUrl: './nav-products.component.html',
  styleUrls: ['./nav-products.component.scss']
})
export class NavProductsComponent implements OnInit {

  @Input() products!: Product[];

  @Output() clickCategory = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {

    console.log(this.products)
  }

  search(e: Event){
    const target = e.target as HTMLInputElement

    const value = target.value

    if(value.length === 0){
      return;
    }

  }

}
