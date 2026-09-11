import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthServiceService } from '../services/auth/auth-service.service';

export const guardGuard: CanActivateFn = (route, state) => {

  // Inyección de dependencias

  const authService = inject(AuthServiceService);
  const router = inject(Router);
  const token: string | null = authService.obtenerAccessToken();

  // Comprobaciones sobre el token

  if(token) {
    return true;

  } // if

  // En caso de no tener valor del token redirigir a página principal

  return router.createUrlTree(['/home']);

};
