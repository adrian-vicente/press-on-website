import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../../model/Login/AuthResponse';
import { Observable } from 'rxjs';
import { LoginRequest } from '../../model/Login/LoginRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/auth";
  constructor(private http: HttpClient) { }

  // Método que permite iniciar sesión en la aplicación

  public login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, request);

  }

  // Método para guardar el token en localStorage

  public guardarToken(token: string): void {
    localStorage.setItem('token', token);

  }

  // Método para obtener token guardado

  public obtenerToken(): string | null {
    return localStorage.getItem('token');

  }

  // Método para saber si el usuario está autenticado

  public estaAutenticado(): boolean {
    return this.obtenerToken != null;

  }

  // Método para cerrar sesión en la aplicación

  public logout(): void {
    localStorage.removeItem('token');

  }

} // class
