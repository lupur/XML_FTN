import { User } from './auth/user';
import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { RoleType } from './auth/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User;

  constructor(private authService: AuthService) {
    this.authService.currentUser
      .subscribe(user => this.currentUser = user);
  }

  get isAuthorized() {
    return this.authService.isAuthorized();
  }

  get isAdmin() {
    return this.authService.hasRole(RoleType.Admin);
  }

  logout() {
    this.authService.logout();
  }
}
