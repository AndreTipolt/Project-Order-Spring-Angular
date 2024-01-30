import { Component, ElementRef, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { Product } from '../../types/Product.interface';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.scss']
})
export class ListProductsComponent implements OnInit {

  @Input() products!: Product[];

  @Output() searchMoreProducts = new EventEmitter<boolean>();

  isRefreshing: boolean = false;

  constructor(private el: ElementRef) { }

  ngOnInit(): void {

  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event) {

    if(this.isRefreshing) return;
    // Altura total da página
    const totalHeight = document.body.offsetHeight;
    
    // Altura da janela do navegador
    const windowHeight = window.innerHeight;
    
    // Posição atual de rolagem vertical
    const scrollPosition = window.scrollY || window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    
    // Porcentagem da altura da página que o usuário alcançou
    const scrolledPercentage = (scrollPosition + windowHeight) / totalHeight * 100;

    // Se o usuário rolar até 70% da página, execute o comando Angular
    if (scrolledPercentage >= 80) {
      this.executeCommand();
      this.isRefreshing = false
    }
  }

  executeCommand() {
    // Execute o comando Angular aqui
    this.searchMoreProducts.emit(true)
    this.isRefreshing= true
  }

}
