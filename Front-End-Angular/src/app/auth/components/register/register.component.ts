import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { HttpStatusCode } from '@angular/common/http';

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
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.title.setTitle("Spring - Cadastrar-se")
  }

  onSubmit(){

    
    if(this.formUser.invalid){
      return;
    }

    if(this.formUser.get('password')?.value != this.formUser.get('confirmPassword')?.value){
      this.messageForm = "Senhas não coincidem";
      return;
    }

    this.messageForm = ""
    this.showSpinnerLoading = true;

    this.authService.register(this.formUser.value).subscribe({
      error: (res) => {
        console.log(res)
        if(res.status === HttpStatusCode.BadRequest){
          this.onError("Dados do formulário invalidos")
        }
      },
      next: (res) => {

        // Implements
      }
    })
  }

  onError(message: string){
    return this.dialog.open(ErrorDialogComponent, {
      data: message
    })
  }

  getErrorMessage(fieldName: string) {

    const field = this.formUser.get(fieldName);

    if (field?.hasError('required')) return "Campo obrigatório";

    if (field?.hasError('email')) return "Email Inválido";

    if (field?.hasError('minlength')) return "Senha muito curta";

    if(this.formUser.get('password')?.value != this.formUser.get('confirmPassword')?.value) return "Senhas não coincidem"

    return "Erro";
  }

  getDiameterSpinnerLoading(): number{
    return 50
  }

}
