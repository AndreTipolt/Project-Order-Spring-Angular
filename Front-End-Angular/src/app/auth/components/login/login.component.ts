import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { AuthService } from '../../services/auth.service';
import { HttpStatusCode } from '@angular/common/http';
import { Title } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';
import { TokenSucessLogin } from '../../types/TokenSucessLogin.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({

    email: new FormControl<string | null>('', [Validators.email, Validators.required]),
    password: new FormControl<string | null>('', [Validators.required])

  })

  messageForm!: string;

  showSpinnerLoading: boolean = false;

  hide = true

  constructor(private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private authService: AuthService,
    private title: Title,
    private cookieService: CookieService,
    private router: Router) { }

  ngOnInit(): void {

    this.title.setTitle("Spring - Login")
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

    this.messageForm = "";
    this.showSpinnerLoading = true;
    this.authService.login(this.formLogin.value).subscribe({
      error: (error) => {

        if (error.status === HttpStatusCode.BadRequest) {

          this.messageForm = "Usuário e/ou Senha inválidos"
          this.showSpinnerLoading = false;
        }
      },
      next: (res: TokenSucessLogin) => {

        const acessToken = `${res.prefixToken} ${res.acessToken}`

        const expiresDate: Date = new Date(res.expires);

        this.authService.setToken("acess_token", acessToken, expiresDate);

        this.router.navigate(['/']).then(() => {
          window.location.reload()
        })
      }
    })
  }

  onError(message: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }

  getDiameterSpinnerLoading(): number {

    return 50;
  }
}
