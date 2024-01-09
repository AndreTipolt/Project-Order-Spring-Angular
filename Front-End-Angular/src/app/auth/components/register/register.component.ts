import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';

import { ErrorFormRegisterUser } from '../../types/ErrorFormRegisterUser.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
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
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private dialog: MatDialog,
    private router: Router) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Cadastrar-se")
    document.cookie = "defato eu confor"
  }

  onSubmit() {


    if (this.formUser.invalid) {
      return;
    }

    if (this.formUser.get('password')?.value != this.formUser.get('confirmPassword')?.value) {
      this.messageForm = "Senhas não coincidem";
      return;
    }

    this.messageForm = ""
    this.showSpinnerLoading = true;

    this.authService.register(this.formUser.value).subscribe({
      error: (res: HttpErrorResponse) => {

        this.showSpinnerLoading = false;

        let emailAlreadyExists = this.checkEmailAlreadyExists(res);

        if(emailAlreadyExists){
          this.messageForm = "Email já existe"
          
          return;
        }

        if (res.status === HttpStatusCode.UnprocessableEntity) {
          this.messageForm = "Preencha todos os campos do formulário"
        }
      },
      next: (res) => {

        this.router.navigate(['/auth/login'])
      }
    })
  }

  onError(message: string) {
    return this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }

  getErrorMessage(fieldName: string) {

    const field = this.formUser.get(fieldName);

    if (field?.hasError('required')) return "Campo obrigatório";

    if (field?.hasError('email')) return "Email Inválido";

    if (field?.hasError('minlength')) return "Senha muito curta";

    if (this.formUser.get('password')?.value != this.formUser.get('confirmPassword')?.value) return "Senhas não coincidem"

    return "Erro";
  }

  getDiameterSpinnerLoading(): number {
    return 50
  }

  checkEmailAlreadyExists(resposseError: HttpErrorResponse): boolean{

    let emailAlreadyExists: boolean = false;
    resposseError.error?.errors.forEach((error: ErrorFormRegisterUser) => {

      if (error.fieldName == "email" && error.message === "Email already exists") { // Checks if the email user already exists
        emailAlreadyExists = true;

      }
    });

    return emailAlreadyExists;
  }

}
