import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  token!: String;

  constructor(private title: Title,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Alterar Senha')

    this.activatedRoute.queryParams.subscribe((param) => {
      this.token = param['token']
    })

  }

}
