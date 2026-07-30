import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from '../services/auth.service';


/* Implementacion de Guard para Angular version 14 para arriba 
  ver: se usa funcion en vez de clases
*/

/* export const PruebaGuard = () => {

  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.chequearAutenticacionUsuario().pipe(
    tap(valido => console.log(valido)),
    tap(valido =>{
      if(!valido){
        router.navigateByUrl('/auth/login');
      }
    })
  )
} */

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(private router: Router,
    private authService: AuthService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.authService.chequearAutenticacionUsuario()
      .pipe(
        tap(valido => {
          if (!valido) {
            this.router.navigateByUrl('/auth/login');
          }
        })
      );
  }

  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | boolean {
    return this.authService.chequearAutenticacionUsuario()
      .pipe(
        tap(valido => {
          if (!valido) {
            this.router.navigateByUrl('/auth/login');
          }
        })
      );
  }
}
