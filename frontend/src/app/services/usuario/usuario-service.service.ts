import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../../model/Usuario/Usuario';
import { UsuarioUpdate } from '../../model/Usuario/UsuarioUpdate';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiceService {

  // Inyección de dependencias

  private http: HttpClient = inject(HttpClient);

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/usuarios";

  // Método para modificar los datos de un usuario

  public modificarUsuarioExistente(usuarioModificado: UsuarioUpdate): Observable<Usuario> {
    return this.http.put<Usuario>(
      `${this.apiUrl}/modificar`,
      usuarioModificado,
      {
        withCredentials: true
      }
    );

  }

} // class
