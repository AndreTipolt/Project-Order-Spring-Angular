import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  messageForm!: string;

  showSpinnerLoading: boolean = false;

  formForgotPassword: FormGroup = this.formBuilder.group({
    email: new FormControl<string>('', [Validators.email, Validators.required])
  })

  constructor(private formBuilder: FormBuilder,
              private title: Title,
              private authService: AuthService) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Esqueci minha senha")
  }

  onSubmit(){

    if (this.formForgotPassword.invalid) return;

    this.showSpinnerLoading = true

    this.authService.forgotPassword(this.formForgotPassword.value).subscribe({
      error: (error: HttpErrorResponse) => {

        console.log(error)
      },

      next: (res) => {
        console.log(res)
      }
    })
  }

  getErrorMessage(fieldName: string){
    
    const field = this.formForgotPassword.get(fieldName);

    if(field?.hasError("required")){
      return "Preencha o campo corretamente"
    }
    else if(field?.hasError("email")){
      return "Digite um email válido"
    }

    return "Erro"
  }

  getDiameterSpinnerLoading(): number{
    return 50;
  }
}
