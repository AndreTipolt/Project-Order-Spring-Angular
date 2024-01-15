import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusPayment'
})
export class StatusPaymentPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    
    switch(value){
      case (value == "WAIT_PAYMENT"): return "Aguardando Pagamento"
      case (value == "PAID"): return "Pago"
      case (value == "DELIVERED"): return "Entregue"
      case (value == "CANCELED"): return "Cancelado"
      default: return 'Aguardando Pagamento' 
    }
  }

}
