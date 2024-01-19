import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusPayment'
})
export class StatusPaymentPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    
    switch(value){
      case ("WAIT_PAYMENT"): return "Aguardando Pagamento"
      case ("PAID"): return "Pago"
      case ("DELIVERED"): return "Entregue"
      case ("CANCELED"): return "Cancelado"
      default: return 'Aguardando Pagamento' 
    }
  }

}
