import { Pipe, PipeTransform } from '@angular/core';
import { DataHeader } from 'src/app/user/types/DataHeader.interface';
import { Notification } from 'src/app/user/types/Notification.interface';

@Pipe({
  name: 'notification'
})
export class NotificationPipe implements PipeTransform {

  transform(notifications: Notification[], ...args: unknown[]): number | string{
    
    if(notifications.length === 0){
      return "";
    }
    
    return notifications.length;
  }

}
