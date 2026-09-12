import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../../model/Usuario/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/auth";
  constructor(private http: HttpClient) { }

  // Método que permite iniciar sesión en la app

  // Método que permite cerrar sessión en la app

  // Método que permite obtener usuario autenticado

  public obtenerUsuarioAutenticado(): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/me`);

  }

} // class
