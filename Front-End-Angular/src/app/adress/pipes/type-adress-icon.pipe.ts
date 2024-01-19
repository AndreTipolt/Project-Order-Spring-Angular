import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'typeAdressIcon'
})
export class TypeAdressIconPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): string {

    switch (value) {
      case ("HOME"): return "home";
      case ("WORK"): return "work";
      default: return "home";
    }
  }
}
