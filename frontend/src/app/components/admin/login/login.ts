import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginRequest } from '../../../model/Login/LoginRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class Login implements OnInit {

  // Inyección de dependencias

  private authService = inject(AuthServiceService);
  constructor(http: HttpClient, public router: Router) {}

  // Declaración de variables

  public email: string = "";
  public password: string = "";

  // Implementación de métodos de la interfaz

  ngOnInit(): void {
    if(localStorage.getItem('token') !== null ) {
      this.router.navigate(['/admin-dashboard']);

    } // if

  }

  // Método para iniciar sesión en la app

  public iniciarSesion(): void {

    // Construir el objeto con los datos rellenados en el formulario

    const loginRequest: LoginRequest = {
      email: this.email,
      password: this.password

    };

    // Hacer la petición al método desarrollado en el servicio

    this.authService.login(loginRequest).subscribe({
      next: (authResponse) => {
        this.authService.guardarToken(authResponse.token);
        console.log("El inicio de sesión se ha ejecutado correctamente.");
        this.router.navigate(['/admin-dashboard']);
      },
      error: (error_login) => {
        console.log("Se ha producido un error iniciando sesión: " + error_login.message);
      }
    });

  }

} // class
