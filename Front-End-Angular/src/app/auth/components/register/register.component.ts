import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  formUser: FormGroup = this.formBuilder.group({

    name: new FormControl<string | null>('', [Validators.required]),
    email: new FormControl<string | null>('', [Validators.email, Validators.required]),
    password: new FormControl<string | null>('', [Validators.minLength(5), Validators.required]),
    confirmPassword: new FormControl<string | null>('', [Validators.minLength(5), Validators.required]),
  })

  messageForm: string = ""

  showSpinnerLoading: boolean = false;

  constructor(private title: Title,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Cadastrar-se")
  }

  onSubmit(){

  }

  getErrorMessage(fieldName: string) {

    const field = this.formUser.get(fieldName);

    if (field?.hasError('required')) return "Campo obrigatório";

    if (field?.hasError('email')) return "Email Inválido";

    if (field?.hasError('minlength')) return "Senha muito curta";

    return "Erro";
  }

  getDiameterSpinnerLoading(): number{
    return 50
  }

}
