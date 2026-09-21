import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-header',
  imports: [CommonModule],
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css',
  standalone: true
})
export class AdminHeaderComponent {

  // Inyección de dependencias

  // Declaración de variables

  public home_url: string = "/home";
  public edit_profile: string = "/admin-edit-profile";

} // class
