import { HttpInterceptorFn } from '@angular/common/http';
import { AuthServiceService } from '../services/auth/auth-service.service';
import { inject } from '@angular/core';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  // Inyección de dependencias

  const authService: AuthServiceService = inject(AuthServiceService);
  const accessToken: string | null = authService.obtenerAccessToken();

  if(accessToken) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`
      }
    });
  }

  return next(req);
};
