import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cart'
})
export class CartPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string | number {

    if(value === 0) return "";

    return value;
  }

}
