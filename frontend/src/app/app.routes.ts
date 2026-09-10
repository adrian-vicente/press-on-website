import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';

export const routes: Routes = [

  // Redirección a página principal

  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomeComponent }

];
