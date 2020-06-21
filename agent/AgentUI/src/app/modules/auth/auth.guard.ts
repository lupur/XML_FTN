import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authService.userValue;
    if (currentUser) {
      if (route.data.roles && !currentUser.roles.some(r => route.data.roles.includes(r.name))) {
        // unauthorized if route is protected with roles
        // and the user does not have required roles

        this.router.navigate(['/']);
        return false;
      }

      // authorized.
      return true;
    }

    this.router.navigate(['/auth/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }

}
