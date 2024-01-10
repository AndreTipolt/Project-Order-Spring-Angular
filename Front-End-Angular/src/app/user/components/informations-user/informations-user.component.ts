import { Component, Input, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { UserResponse } from '../../types/UserResponse.interface';

@Component({
  selector: 'app-informations-user',
  templateUrl: './informations-user.component.html',
  styleUrls: ['./informations-user.component.scss']
})
export class InformationsUserComponent implements OnInit {

  @Input() currentUser!: UserResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
