import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent{
  email = '';
  password = '';
  username = '';
  isRegister = false;
  error = '';

  constructor(private authService: AuthService,private router: Router) {}

  submit(){
    this.error = '';
    if (this.isRegister){
      this.authService.register(this.username, this.email, this.password).subscribe({
          next: () => this.router.navigate(['/']),
          error: () => this.error = 'Ошибка регистрации'
        });
    }else{
      this.authService.login(this.email, this.password).subscribe({
          next: () => this.router.navigate(['/']),
          error: () => this.error = 'Неверный email или пароль'
        });
    }
  }

  toggleMode(){
    this.isRegister = !this.isRegister;
  }
}
