import { Component, OnInit } from '@angular/core';
import { User } from '@app/users/user';
import { AuthService } from '@app/auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.userValue;
  }

}
