import { Pipe, PipeTransform } from '@angular/core';
import { DataHeader } from 'src/app/user/types/DataHeader.interface';
import { Notification } from 'src/app/user/types/Notification.interface';
import { NotificationUser } from 'src/app/user/types/NotificationUser.interface';

@Pipe({
  name: 'notification'
})
export class NotificationPipe implements PipeTransform {

  transform(numberOfNotifications: number, ...args: unknown[]): number | string{
    
    if(numberOfNotifications === 0){
      return ''
    }

    return numberOfNotifications;
    
  }

}
