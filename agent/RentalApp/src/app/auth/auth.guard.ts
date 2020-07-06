import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService } from './auth.service';
import { RoleType } from './role';


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
    if (roles && !roles.some(r => this.authService.hasRole(r))){
      this.router.navigate(['/']);
      return false;
    }
    
    return true;

    // const currentUser = this.authService.userValue;
    // if (currentUser) {
    //   if (route.data.roles && !currentUser.roles.some(r => route.data.roles.includes(r.name))) {
    //     this.router.navigate(['/']);
    //     return false;
    //   }
    //   return true;
    // }

    // this.router.navigate(['/auth/login'], { queryParams: { returnUrl: state.url } });
    // return false;
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
