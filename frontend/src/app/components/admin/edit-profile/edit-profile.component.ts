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

  public usuario: Usuario = {
    id: 0,
    nombre: '',
    apellidos: '',
    fotoPerfil_url: '',
    email: '',
    password: '',
    fechaCreacion: new Date(),
    fechaActualizacion: new Date(),
    rol: '',
  };

  public usuarioModificado: UsuarioUpdate = {
    id: 0,
    nombre: '',
    apellidos: '',
    fotoPerfil_url: '',
    email: '',
    password: '',
    fechaCreacion: new Date(),
    fechaActualizacion: new Date(),
    rol: '',
  };

  // Implementar métodos de la interdaz

  ngOnInit(): void {

    // Obtener el usuario actual de la sesión

    this.authService.obtenerUsuarioAutenticado().subscribe({
      next: (usuario_api) => {
        this.usuario = usuario_api;

        // Trasapasar los datos necesarios para modificar el usuario

        this.usuarioModificado.id = usuario_api.id;
        this.usuarioModificado.fechaCreacion = usuario_api.fechaCreacion;
        this.usuarioModificado.fotoPerfil_url = "no_implementado";
        this.usuarioModificado.rol = usuario_api.rol;

        // Mensaje en la consola

        console.log("Se ha encontrado al usuario autenticado correctamente.");
      },
      error: (error_api) => {
        console.log("Se ha producido un error: " + error_api.message);
      }
    });

  } // ngOnInit

  // Método que permite actualizar la información de usuario existente

  public modificarUsuarioExistente(): void {

    // Comprobaciones sobre los campos pendientes de usuarioModificado

    if(this.usuarioModificado.nombre === null || this.usuarioModificado.nombre === '') {
      this.usuarioModificado.nombre = this.usuario.nombre;

    } // if

    if(this.usuarioModificado.apellidos === null || this.usuarioModificado.apellidos === '') {
      this.usuarioModificado.apellidos = this.usuario.apellidos;

    } // if

    if(this.usuarioModificado.email === null || this.usuarioModificado.email === '') {
      this.usuarioModificado.email = this.usuario.email;

    } // if

    // Mandar petición a la api para modificar el usuario existente

    this.usuarioService.modificarUsuarioExistente(this.usuarioModificado).subscribe({
      next: (usuario_api) => {
        console.log("El usuario se ha actualizado correctamente.");
        this.router.navigate(['/admin-dashboard']);
      },
      error: (error_api) => {
        console.log("Se ha producido un error: " + error_api.message);
      }
    });

  }


}
