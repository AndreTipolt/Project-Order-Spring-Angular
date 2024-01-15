import { Component, Input, OnInit } from '@angular/core';
import { UserResponse } from '../../types/UserResponse.interface';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-adresses-user',
  templateUrl: './adresses-user.component.html',
  styleUrls: ['./adresses-user.component.scss']
})
export class AdressesUserComponent implements OnInit {

  @Input() currentUser!: UserResponse
  
  constructor(private title: Title) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Meus Endereços")
  }

}
