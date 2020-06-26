import { User } from './auth/user';
import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';

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

  logout() {
    this.authService.logout();
  }
}
