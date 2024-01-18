import { Component, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { UserResponse } from 'src/app/user/types/UserResponse.interface';

@Component({
  selector: 'app-show-adresses',
  templateUrl: './show-adress.component.html',
  styleUrls: ['./show-adress.component.scss']
})
export class ShowAdressComponent implements OnInit {

  @Input() currentUser!: UserResponse

  constructor(private title: Title) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Meus Endereços")
  }

  deleteAdressById(adressId: string){
    console.log('apertou')
  }

}
