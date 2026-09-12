import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  // Indicar en la petición que se envíe la credencial (Cookie)

  const request = req.clone({
    withCredentials: true
  });

  return next(request);

};
