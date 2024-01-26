import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from 'src/app/products/types/Product.interface';

@Component({
  selector: 'app-show-item-cart',
  templateUrl: './show-item-cart.component.html',
  styleUrls: ['./show-item-cart.component.scss']
})
export class ShowItemCartComponent implements OnInit {

  @Input() product!: Product;

  @Output() deleteItemCart = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  onDeleteItemCart(productId: string){
    // if(productId != undefined) return;
    return this.deleteItemCart.emit(productId);
  }

}
