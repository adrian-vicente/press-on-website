import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ProductoServiceService } from '../../../services/producto/producto-service.service';
import { Router } from '@angular/router';
import { Producto } from '../../../model/Producto/Producto';

@Component({
  selector: 'app-producto-list',
  imports: [CommonModule],
  templateUrl: './producto-list.component.html',
  standalone: true,
  styleUrl: './producto-list.component.css'
})
export class ProductoListComponent implements OnInit {

  // Inyección de dependencias

  private productoService: ProductoServiceService = inject(ProductoServiceService);
  private router: Router = inject(Router);

  // Declaración de variables

  public listadoProductos: Producto[] | null = null;

  // Implementación métodos de la interfaz

  ngOnInit(): void {

    // Método que permite obtener listado de todos los productos

    this.productoService.obtenerTodosLosProductos().subscribe({
      next: (listado_productos_api) => {
        this.listadoProductos = listado_productos_api;
        console.log("Se han encontrado: " + listado_productos_api.length + " productos." );
      },
      error: (error_api) => {
        console.log("Se ha producido un error obteniendo todos los productos: " + error_api.message );
      }
    });

  }

} // class
