import { Component, Input, OnInit } from '@angular/core';
import { MenuSubTotalData } from '../../types/MenuSubTotalData.interface';
import { Product } from 'src/app/products/types/Product.interface';

@Component({
  selector: 'app-menu-sub-total-cart',
  templateUrl: './menu-sub-total-cart.component.html',
  styleUrls: ['./menu-sub-total-cart.component.scss']
})
export class MenuSubTotalCartComponent implements OnInit {


  @Input() menuSubTotalData!: MenuSubTotalData;

  constructor() { }

  ngOnInit(): void {
  }

}
