import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { Usuario } from '../../../model/Usuario/Usuario';
import { AuthResponse } from '../../../model/Login/AuthResponse';
import { Route, Router } from '@angular/router';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { UsuarioServiceService } from '../../../services/usuario/usuario-service.service';

@Component({
  selector: 'app-admin-header',
  imports: [CommonModule],
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css',
  standalone: true
})
export class AdminHeaderComponent {

} // class
