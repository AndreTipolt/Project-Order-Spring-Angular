import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenSucessLogin } from '../../types/TokenSucessLogin.interface';
import { MatDialog } from '@angular/material/dialog';
import { SucessDialogComponent } from 'src/app/shared/components/sucess-dialog/sucess-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

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
              private authService: AuthService,
              private router: Router,
              private dialog: MatDialog) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Esqueci minha senha")

  }

  onSubmit(){

    if (this.formForgotPassword.invalid) return;

    this.showSpinnerLoading = true
    this.messageForm = ""

    this.authService.forgotPassword(this.formForgotPassword.value).subscribe({
      error: (error: HttpErrorResponse) => {

        if(error.error.status === HttpStatusCode.NotFound || error.error.status === HttpStatusCode.UnprocessableEntity){

          this.messageForm = "Email inválido"
          this.showSpinnerLoading = false
          return;
        }

        this.messageForm = ""
        this.showSpinnerLoading = false
        this.onError("Erro ao tentar enviar email. Tente mais tarde.")
      },

      next: (res) => {
        
        this.showSpinnerLoading = false
        this.messageForm = ""

        this.onSucess("Uma mensagem foi enviada para seu email", "Mensagem Enviada")
        return;
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

  onSucess(message: string, title: string){
    
    return this.dialog.open(SucessDialogComponent, {
      data: { message: message, title: title }
    })
  }

  onError(message: string){
    return this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }
}
