import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({

    email: new FormControl<string | null>('', [Validators.email, Validators.required]),
    password: new FormControl<string | null>('', [Validators.required])

  })
  constructor(private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  getErrorMessage(fieldName: string) {

    const field = this.formLogin.get(fieldName);

    if (field?.hasError('required')) return "Campo obrigatório";

    if (field?.hasError('email')) return "Email Inválido";

    return "Erro";
  }

  onSubmit() {

    if (this.formLogin.invalid) {
      return;
    }

    this.authService.login(this.formLogin.value).subscribe((res) => {
      console.log(res)
    })
  }

  onError(message: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }
}
