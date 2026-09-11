import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProductoListComponent } from '../../productos/producto-list/producto-list.component';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule, RouterLink, ProductoListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  // Inyección de dependencias

  constructor(public router: Router) {}

  // Declaración de variables

  public dashboard_url: string = "/admin-login";

}
