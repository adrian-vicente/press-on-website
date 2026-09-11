import { Component } from '@angular/core';
import { AdminHeaderComponent } from "../../dise\u00F1o/admin-header/admin-header.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [AdminHeaderComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  standalone: true
})
export class DashboardComponent {

}
