import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { SucessDialogComponent } from 'src/app/shared/components/sucess-dialog/sucess-dialog.component';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  formChangePassword: FormGroup = this.formBuilder.group({
    newPassword: new FormControl<string>('', [Validators.required, Validators.minLength(5)]),
    confirmPassword: new FormControl<string>('', [Validators.required, Validators.minLength(5)])
  })

  token!: string;

  showSpinnerLoading: boolean = false;
  messageForm!: string;

  constructor(private title: Title,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Alterar Senha')

    this.activatedRoute.queryParams.subscribe((param) => {
      this.token = param['token']
    })

  }

  onSubmit() {


    if (this.formChangePassword.invalid) return;

    if (this.formChangePassword.get('newPassword')?.value != this.formChangePassword.get('confirmPassword')?.value) {
      this.messageForm = "Senhas Não Coincidem"
      return;
    }

    const newPassword = this.formChangePassword.get('newPassword')?.value
    const token = this.token

    if (newPassword === null || token === null) {
      return;
    }

    this.messageForm = ""
    this.showSpinnerLoading = true

    this.userService.changePassword(newPassword, token).subscribe({
      error: (error: HttpErrorResponse) => {

        this.onError("Erro ao alterar a senha.")

        return;
      },
      next: (res) => {

        this.onSucess("Senha foi alterada com sucesso", "Senha alterada")
        this.router.navigate(['/auth/login'])
        return;
      }
    })
  }

  getDiameterSpinnerLoading(): number {
    return 50;
  }

  getErrorMessage(fieldName: string) {

    const field = this.formChangePassword.get(fieldName);

    if (field?.hasError('required')) return "Campo obrigatório";

    if (field?.hasError('email')) return "Email Inválido";

    if (field?.hasError('minlength')) return "Senha muito curta";

    if (this.formChangePassword.get('newPassword')?.value != this.formChangePassword.get('confirmPassword')?.value) return "Senhas não coincidem"

    return "Erro";
  }

  onError(message: string) {

    return this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }

  onSucess(message: string, title: string){
    return this.dialog.open(SucessDialogComponent, {
      data: {
        title: title,
        message: message
      }
    })
  }


}
