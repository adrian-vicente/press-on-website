import { Component, inject, OnInit } from '@angular/core';
import { AdminHeaderComponent } from '../../dise\u00F1o/admin-header/admin-header.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../../../model/Usuario/Usuario';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { UsuarioUpdate } from '../../../model/Usuario/UsuarioUpdate';
import { UsuarioServiceService } from '../../../services/usuario/usuario-service.service';
import { Router } from '@angular/router';

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
  private usuarioService: UsuarioServiceService = inject(UsuarioServiceService);
  private router: Router = inject(Router);

  // Declaración de variables

  public usuarioActual: Usuario | null = null;
  public usuarioModificado: UsuarioUpdate = {
    id: 0,
    nombre: '',
    apellidos: '',
    fotoPerfil_url: '',
    email: '',
    password: '',
    fechaCreacion: new Date(),
    fechaActualizacion: new Date(),
    rol: ''
  }

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

  public modificarUsuarioExistente(): void {

    // Comprobaciones sobre los campos rellenados / modificados

    Object.entries(this.usuarioModificado).forEach(([clave, valor]) => {

      if(valor === null || valor === '' || valor === undefined) {
        this.usuarioModificado[clave as keyof typeof this.usuarioModificado] =
          this.usuarioActual![clave as keyof typeof this.usuarioActual];

      } // if

    });

    // Lanzar petición para modificar el usuario

    this.usuarioService.modificarUsuarioExistente(this.usuarioModificado).subscribe({
      next: (usuario_modificado) => {
        console.log("Los datos del usuario han sido actualizados correctamente.");
        this.router.navigate(['/home']);
      },
      error: (error_api) => {
        console.log("Se ha producido un error: " + error_api.message);
        console.log({usuarioModificado: this.usuarioModificado});

      }
    });

  }

}
