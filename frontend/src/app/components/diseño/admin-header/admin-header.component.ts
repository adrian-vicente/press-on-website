import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, inject, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-header',
  imports: [CommonModule],
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css',
  standalone: true
})
export class AdminHeaderComponent implements AfterViewInit {

  // Inyección de dependencias

  private router: Router = inject(Router);

  // Declaración de variables

  public home_url: string = "/home";
  public edit_profile: string = "/admin-edit-profile";

  // Implementar métodos de la interfaz

  ngAfterViewInit(): void {
    let li_elements = document.querySelectorAll(".enlaces ul li");
    li_elements.forEach((li) => {

      // Obtener el elemento a para comprobar el atributo href

      const a = li.querySelector("a");

      // Comprobar el atributo href del elemento obtenido

      if(a?.getAttribute("href") === this.obtenerUrlActual()) {
        (li as HTMLElement).style.display = "none";
      }

    });

  }

  // Método que permite obtener la url actual

  private obtenerUrlActual(): string {
    return this.router.url;
  }

} // class
