import { Component, Input, OnInit } from '@angular/core';
import { UserResponse } from '../../types/UserResponse.interface';

@Component({
  selector: 'app-my-informations',
  templateUrl: './my-informations.component.html',
  styleUrls: ['./my-informations.component.scss']
})
export class MyInformationsComponent implements OnInit {

  @Input() currentUser!: UserResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
