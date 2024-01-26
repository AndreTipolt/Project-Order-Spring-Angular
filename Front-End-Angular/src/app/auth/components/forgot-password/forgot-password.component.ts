import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenSucessLogin } from '../../types/TokenSucessLogin.interface';
import { MatDialog } from '@angular/material/dialog';
import { SucessDialogComponent } from 'src/app/shared/components/sucess-dialog/sucess-dialog.component';

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
    this.onSucess("Uma mensagem foi enviada para seu email", "Mensagem Enviada")

  }

  onSubmit(){

    if (this.formForgotPassword.invalid) return;

    this.showSpinnerLoading = true

    this.authService.forgotPassword(this.formForgotPassword.value).subscribe({
      error: (error: HttpErrorResponse) => {

        this.messageForm = "Email inválido"
        this.showSpinnerLoading = false
      },

      next: (res: TokenSucessLogin) => {
        this.showSpinnerLoading = false

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
}
