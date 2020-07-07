import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot } from '@angular/router';
import { RoleType } from '@app/roles/role';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {
  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.authService.isAuthorized()) {
      this.router.navigate(['/auth/login']);
      return false;
    }
    const roles = route.data.roles as RoleType[];
    if (roles && !roles.some(r => this.authService.hasRole(r))) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }

  canLoad(route: Route): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authService.isAuthorized()) {
      return false;
    }

    const roles = route.data && route.data.roles as RoleType[];
    if (roles && !roles.some(r => this.authService.hasRole(r))) {
      return false;
    }

    return true;
  }

}
