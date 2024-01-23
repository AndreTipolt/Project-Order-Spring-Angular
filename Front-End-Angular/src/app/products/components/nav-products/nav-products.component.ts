import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from '../../../category/types/Category.interface';

@Component({
  selector: 'app-nav-products',
  templateUrl: './nav-products.component.html',
  styleUrls: ['./nav-products.component.scss']
})
export class NavProductsComponent implements OnInit {

  @Input() categories!: Category[];

  @Output() clickCategory = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {
  }

}
