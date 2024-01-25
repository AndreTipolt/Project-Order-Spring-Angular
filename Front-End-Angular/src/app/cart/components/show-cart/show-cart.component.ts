import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-show-cart',
  templateUrl: './show-cart.component.html',
  styleUrls: ['./show-cart.component.scss']
})
export class ShowCartComponent implements OnInit {

  constructor(private title: Title) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Carrinho')
  }

}
