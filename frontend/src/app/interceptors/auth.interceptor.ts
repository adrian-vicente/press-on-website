import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { AuthServiceService } from '../services/auth/auth-service.service';
import { inject } from '@angular/core';
import { catchError, switchMap, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  // Inyección de dependencias

  const authService: AuthServiceService = inject(AuthServiceService);

  // Declaración de variables

  const rutasPublicas = [
    '/api/auth/login',
    '/api/auth/refresh',
    '/api/productos',
    '/api/colecciones'
  ];

  // Comprobar si la ruta es pública

  const esPublica = rutasPublicas.some(ruta =>
    req.url.includes(ruta)
   );

   if(esPublica) {
    return next(req);

   } // if

  // Comprobación sobre el accessToken

   const accessToken = authService.obtenerAccessToken();

  if(accessToken) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`
      }
    });
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {

      // Detectar si el error es código 401

      if(error.status !== 401) {
        return throwError(() => error);
      }

      // Acess token caducado
      // Generar un refresh e intentar de nuevo la petición

      return authService.refreshToken().pipe(
        switchMap((authResponse) => {

          // Guardar los nuevos tokens

          authService.guardarTokens(authResponse);

          // Repetir la petición con el nuevo access token

          const newRequest = req.clone({
            setHeaders: {
              Authorization: `Bearer ${authResponse.accesstoken}`
            }
          });

          return next(newRequest);

        }),

        // En caso de fallar el refresh token cerrar la sesión

        catchError((refreshError) => {
          authService.logout();
          return throwError(() => refreshError);
        })

      );

    })

  );

};
