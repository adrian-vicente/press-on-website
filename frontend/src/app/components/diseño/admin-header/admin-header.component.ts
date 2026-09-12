import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { Usuario } from '../../../model/Usuario/Usuario';
import { AuthResponse } from '../../../model/Login/AuthResponse';
import { Route, Router } from '@angular/router';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { UsuarioServiceService } from '../../../services/usuario/usuario-service.service';

@Component({
  selector: 'app-admin-header',
  imports: [CommonModule],
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css',
  standalone: true
})
export class AdminHeaderComponent implements OnInit {

  // Inyección de dependencias

  private usuarioService: UsuarioServiceService = inject(UsuarioServiceService);
  private router: Router = inject(Router);

  // Declaración de variables

  public enlaceHome: string = "/home";
  public enlaceEditarPerfil: string = "";
  public enlaceHistorialPedidos: string = "";
  public usuarioAutenticado: Usuario | null = null;

  // Implementar métodos de la interfaz

  ngOnInit(): void {

    if(localStorage.getItem('accessToken') === null) {
      this.router.navigate(['/home']);

    } //if

    let authResponse: AuthResponse = {
      accesstoken: localStorage.getItem('accessToken')!,
      refreshToken: localStorage.getItem('refreshToken')!

    };

    this.usuarioService.obtenerUsuarioAutenticado(authResponse).subscribe({
      next: (usuarioAutenticado_api) => {
        this.usuarioAutenticado = usuarioAutenticado_api;
        console.log("El usuario: " + usuarioAutenticado_api.nombre + " se ha obtenido correctamente.");
      },
      error: (error_api) => {
        console.log(error_api.message);
      }
    });

  }

} // class
