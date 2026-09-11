import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { ColeccionDetailComponent } from './components/colecciones/coleccion-detail/coleccion-detail.component';
import { ColeccionFormComponent } from './components/colecciones/coleccion-form/coleccion-form.component';
import { ColeccionListComponent } from './components/colecciones/coleccion-list/coleccion-list.component';
import { ProductoDetailComponent } from './components/productos/producto-detail/producto-detail.component';
import { ProductoFormComponent } from './components/productos/producto-form/producto-form.component';
import { ProductoListComponent } from './components/productos/producto-list/producto-list.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { Login } from './components/admin/login/login';
import { guardGuard } from './guards/guard.guard';

export const routes: Routes = [

  // Redirección a página principal

  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },

  // Declaración de rutas para componente productos

  { path: 'coleccion-detalle', component: ColeccionDetailComponent},
  { path: 'coleccion-formulario', component: ColeccionFormComponent},
  { path: 'coleccion-lista', component: ColeccionListComponent},

  // Declaración de rutas para componente colecciones

  { path: 'producto-detalle', component: ProductoDetailComponent},
  { path: 'producto-formulario', component: ProductoFormComponent},
  { path: 'producto-lista', component: ProductoListComponent},

  // Declaración de rutas para componente usuario

  { path: 'admin-dashboard',
    component: DashboardComponent,
    canActivate: [guardGuard]
  },
  {
    path: 'admin-login',
    component: Login
  }

];
