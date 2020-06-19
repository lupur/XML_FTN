import { Component } from '@angular/core';
import { User } from '@app/models';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  user: User;

  constructor(private authService: AuthService) {
    this.user = this.authService.userValue;
  }

}
