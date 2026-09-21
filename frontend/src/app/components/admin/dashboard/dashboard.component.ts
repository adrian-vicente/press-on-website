import { Component, inject, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../../dise\u00F1o/admin-header/admin-header.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { Usuario } from '../../../model/Usuario/Usuario';


@Component({
  selector: 'app-dashboard',
  imports: [AdminHeaderComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  standalone: true
})
export class DashboardComponent implements OnInit {

  // Inyección de dependencias

  private http: HttpClient = inject(HttpClient);
  private authService: AuthServiceService = inject(AuthServiceService);

  // Declaración de variables

  public usuario: Usuario | null = null;

  // Implementación método de la interfaz

  ngOnInit(): void {

    // Método para obtener al usuario autenticado

    this.authService.obtenerUsuarioAutenticado().subscribe({
      next: (usuarioAutenticado_api) => {
        this.usuario = usuarioAutenticado_api;
        console.log("Se ha obtenido el usuario: " + usuarioAutenticado_api.nombre);
      },
      error: (error_api) => {
        console.error("Se ha producido un error: " + error_api.message);
      }
    });

  }

} // class
