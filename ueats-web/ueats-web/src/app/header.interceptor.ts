import { HttpInterceptorFn } from '@angular/common/http';

export const headerInterceptor: HttpInterceptorFn = (req, next) => {
  const clonedRequest = req.clone({
    setHeaders: {
      'Your-Header-Name': 'Your-Header-Value'
    }
  });

  return next(clonedRequest);
};
