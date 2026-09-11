import { Component, inject, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../../dise\u00F1o/admin-header/admin-header.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from '../../../services/auth/auth-service.service';
import { Usuario } from '../../../model/Usuario/Usuario';
import { Router } from '@angular/router';
import { AuthResponse } from '../../../model/Login/AuthResponse';

@Component({
  selector: 'app-dashboard',
  imports: [AdminHeaderComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  standalone: true
})
export class DashboardComponent  {

} // class
