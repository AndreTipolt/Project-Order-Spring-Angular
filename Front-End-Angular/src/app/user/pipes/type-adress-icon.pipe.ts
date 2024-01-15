import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'typeAdressIcon'
})
export class TypeAdressIconPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {

    switch (value) {
      case ("HOME"): return "home";
      case ("WORK"): return "work";
      default: return "home";
    }
  }

}
