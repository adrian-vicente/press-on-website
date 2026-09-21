import { Component } from '@angular/core';
import { AdminHeaderComponent } from '../../dise\u00F1o/admin-header/admin-header.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-profile',
  imports: [AdminHeaderComponent, CommonModule, FormsModule],
  templateUrl: './edit-profile.component.html',
  standalone: true,
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent {

}
