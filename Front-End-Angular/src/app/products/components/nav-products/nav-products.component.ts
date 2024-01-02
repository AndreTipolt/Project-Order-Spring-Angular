import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../types/Category.interface';

@Component({
  selector: 'app-nav-products',
  templateUrl: './nav-products.component.html',
  styleUrls: ['./nav-products.component.scss']
})
export class NavProductsComponent implements OnInit {

  @Input() categories!: Category[];
  constructor() { }

  ngOnInit(): void {
  }

}
