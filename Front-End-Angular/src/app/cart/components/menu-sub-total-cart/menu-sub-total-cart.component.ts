import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MenuSubTotalData } from '../../types/MenuSubTotalData.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-sub-total-cart',
  templateUrl: './menu-sub-total-cart.component.html',
  styleUrls: ['./menu-sub-total-cart.component.scss']
})
export class MenuSubTotalCartComponent implements OnInit {


  @Input() menuSubTotalData!: MenuSubTotalData;

  @Output() finishOrder = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  onFinishOrder(){
    return this.finishOrder.emit(true)
  }

  getMessageText(){

  }

}
