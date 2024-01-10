import { Component, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { UserService } from '../../services/user.service';
import { UserResponse } from '../../types/UserResponse.interface';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser!: UserResponse
  
  constructor(private title: Title,
              private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.title.setTitle('Spring - Meu Perfil')

    this.userService.getUserData().subscribe({
      error: (error: HttpErrorResponse) => {

        this.router.navigate(['/auth/login']);
      },
      next: (res) => {

        this.currentUser = res;
      }
    })
  }

}
