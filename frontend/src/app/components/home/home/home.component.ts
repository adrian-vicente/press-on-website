import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductoListComponent } from '../../productos/producto-list/producto-list.component';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule, ProductoListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
