import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { UserResponse } from 'src/app/user/types/UserResponse.interface';
import { AdressService } from '../../services/adress.service';

@Component({
  selector: 'app-add-adress',
  templateUrl: './add-adress.component.html',
  styleUrls: ['./add-adress.component.scss']
})
export class AddAdressComponent implements OnInit {

  @Input() currentUser!: UserResponse;

  formAddAdress: FormGroup = this.formBuilder.group({

    name: new FormControl<string | null>('', [Validators.required]),
    cep: new FormControl<string | null>('', [Validators.required, Validators.maxLength(8), Validators.minLength(8)]),
    street: new FormControl<string | null>('', [Validators.required]),
    number: new FormControl<string | null>('', [Validators.required]),
    neighborhood: new FormControl<string | null>('', [Validators.required]),
    city: new FormControl<string | null>('', [Validators.required]),
    typeAdress: new FormControl<string | null>('', [Validators.required]),
    state: new FormControl<string | null>('', [Validators.required]),
    complement: new FormControl<string | null>(''),

  })
  constructor(private title: Title,
    private formBuilder: FormBuilder,
    private adressService: AdressService) { }

  ngOnInit(): void {

    this.title.setTitle('Spring - Adicionar Endereço')
  }

  onSubmit() {

  }

  searchCEP() {

    let cep: string = this.formAddAdress.get('cep')?.value

    if (this.validateCep(cep)) {

      this.adressService.getInformationsAdress(cep).subscribe({
        next: (res) => {

          if (res.erro) {

          }

          console.log(res)

        }
      })
    }

  }

  validateCep(cep: string): boolean {

    cep = cep.replace(/\D/g, '')

    if (cep != null) {

      let validateCep = /^[0-9]{8}$/

      if (validateCep.test(cep)) {

        return true
      }
    }

    return false
  }


}
