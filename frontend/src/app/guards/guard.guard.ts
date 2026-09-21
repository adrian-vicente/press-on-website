import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthServiceService } from '../services/auth/auth-service.service';
import { catchError, map, of } from 'rxjs';

export const guardGuard: CanActivateFn = (route, state) => {

  // Inyección de dependencias

  const authService: AuthServiceService = inject(AuthServiceService);
  const router: Router = inject(Router);

  // Obtener el usuario autenticado y aplicar redirecciones

  return authService.obtenerUsuarioAutenticado().pipe(
    map(usuario => {
      if(usuario.rol === 'ADMIN') {
        return true;

      } // if

      return router.createUrlTree(['/home']);

    }),

    catchError(() => {
      return of(
        router.createUrlTree(['/home'])
      );

    })

  );

};
