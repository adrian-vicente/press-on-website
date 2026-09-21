import { Component, inject, OnInit } from '@angular/core';
import { AdminHeaderComponent } from '../../dise\u00F1o/admin-header/admin-header.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../../../model/Usuario/Usuario';
import { AuthServiceService } from '../../../services/auth/auth-service.service';

@Component({
  selector: 'app-edit-profile',
  imports: [AdminHeaderComponent, CommonModule, FormsModule],
  templateUrl: './edit-profile.component.html',
  standalone: true,
  styleUrl: './edit-profile.component.css',
})
export class EditProfileComponent implements OnInit {

  // Inyección de dependencias

  private authService: AuthServiceService = inject(AuthServiceService);

  // Declaración de variables

  public usuarioActual: Usuario | null = null;

  // Implementar métodos de la interfaz

  ngOnInit(): void {
    // Obtener el usuario autenticado

    this.authService.obtenerUsuarioAutenticado().subscribe({
      next: (usuario_api) => {
        this.usuarioActual = usuario_api;
        console.log('Se ha encontrado el usuario autenticado sin problemas.');
      },
      error: (error_api) => {
        console.error(error_api.message);
      },
    });
  }

  // Método que permite modificar el usuario autenticado

}
