import { Component } from '@angular/core';
import { User } from './models';
import { AuthService } from './modules/auth/auth.service';
import { RoleType } from './models/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user: User;

  constructor(private authService: AuthService) {
    this.authService.user.subscribe(x => this.user = x);
  }

  get isAdmin() {
    return this.user && this.user.roles.some(r => r.name === RoleType.Administrator);
  }

  logout() {
    this.authService.logout();
  }
}
