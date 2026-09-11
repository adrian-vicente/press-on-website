import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Producto } from '../../model/Producto/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoServiceService {

  // Inyección de dependencias

  private http: HttpClient = inject(HttpClient);
  private router: Router = inject(Router);

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/productos";

  // Método para obtener listado de todos los productos

  public obtenerTodosLosProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.apiUrl}`);

  }

}
