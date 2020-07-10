import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { RoleType } from './roles/role';
import { User } from './users/user';

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

  get isAgent() {
    return this.authService.hasRole(RoleType.Agent);
  }
  logout() {
    this.authService.logout();
  }
}
