import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../../model/Login/AuthResponse';
import { Observable } from 'rxjs';
import { LoginRequest } from '../../model/Login/LoginRequest';
import { Usuario } from '../../model/Usuario/Usuario';
import { RefreshTokenRequest } from '../../model/Login/RefreshTokenRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/auth";
  constructor(private http: HttpClient) { }

  // Método que permite iniciar sesión en la aplicación

  public login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${this.apiUrl}/login`,
       request
    );

  }

  // Método para guardar el token en localStorage

  public guardarTokens(authResponse: AuthResponse): void {
    localStorage.setItem('accessToken', authResponse.accesstoken);
    localStorage.setItem('refreshToken', authResponse.refreshToken);

  }

  // Método para obtener token guardado

  public obtenerAccessToken(): string | null {
    return localStorage.getItem('accessToken');

  }

  public obtenerRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');

  }

  // Método para saber si el usuario está autenticado

  public estaAutenticado(): boolean {
    return this.obtenerAccessToken !== null && this.obtenerRefreshToken !== null;

  }

  // Método para cerrar sesión en la aplicación

  public logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');

  }

  // Método para obtener un refresh token

  public refreshToken(): Observable<AuthResponse> {
    const refreshToken = this.obtenerRefreshToken();

    if(refreshToken === null) {
      throw new Error('No existe refresh token');

    } // if

    const request: RefreshTokenRequest = {
      refreshToken: refreshToken

    };

    return this.http.post<AuthResponse>(
      `${this.apiUrl}/refresh`,
      request
    );

  }

} // class
