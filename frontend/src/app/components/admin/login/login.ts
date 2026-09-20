import { Component, inject } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../../model/Login/LoginRequest';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class Login {

  // Inyección de dependencias

  private authService: AuthServiceService = inject(AuthServiceService);
  private router: Router = inject(Router);

  // Declaración de variables

  public loginRequest: LoginRequest = {
    email: '',
    password: ''
  }

  // Método que permite iniciar sesión en la aplicación

  public iniciarSesion(): void {
    if(!this.loginRequest.email || !this.loginRequest.password) {
      alert('Debes rellenar los campos para iniciar sesión');
      return;

    } // if

    this.authService.iniciarSesion(this.loginRequest).subscribe({
      next: (mensaje_api) => {
        console.log("Inicio de sesión correcto", mensaje_api);
        this.router.navigate(['/admin-dashboard']);
      },
      error: (error_api) => {
        console.log("Se ha producido un error en el login: " + error_api.message);

      }
    });

  }

} // class
