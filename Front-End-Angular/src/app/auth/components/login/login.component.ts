import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({

    email: new FormControl<string | null>('', [ Validators.email, Validators.required ]),
    password: new FormControl<string | null>('', [ Validators.required ])

  })
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

}
