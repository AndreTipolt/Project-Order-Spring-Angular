import { Component, Input, OnInit } from '@angular/core';
import { UserResponse } from '../../types/UserResponse.interface';

@Component({
  selector: 'app-orders-user',
  templateUrl: './orders-user.component.html',
  styleUrls: ['./orders-user.component.scss']
})
export class OrdersUserComponent implements OnInit {

  @Input() currentUser!: UserResponse;
  constructor() { }

  ngOnInit(): void {
  }

}
