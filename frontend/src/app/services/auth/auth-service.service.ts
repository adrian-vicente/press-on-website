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

} // class
