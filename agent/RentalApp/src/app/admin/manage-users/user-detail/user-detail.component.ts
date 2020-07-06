import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@app/auth/auth.service';
import { AccountStatus } from '@app/auth/user';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  userId: number;
  user = null;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['id'];
    this.authService.getById(this.userId)
      .pipe(first())
      .subscribe(user => this.user = user);
  }

  get isActive() {
    return this.user.status == AccountStatus.ACTIVE;
  }
  get isBlocked() {
    return this.user.status == AccountStatus.BLOCKED;
  }

  activate(id: number) {
    this.user.isActivating = true;

    this.authService.activate(id)
      .pipe(first())
      .subscribe(_ => {
        this.gotoUsers();
        this.alertService.success('User activated successfully!', {
          keepAfterRouteChange: true, autoClose: true
        });
      }, error => {
        this.user.isActivating = false;
        this.alertService.error(error);
      });
  }

  block(id: number) {
    this.user.isBlocking = true;

    this.authService.block(id)
      .pipe(first())
      .subscribe(_ => {
        this.gotoUsers();
        this.alertService.success('User blocked successfully!', {
          keepAfterRouteChange: true, autoClose: true
        });
      }, error => {
        this.user.isBlocking = false;
        this.alertService.error(error);
      });
  }

  gotoUsers() {
    this.router.navigate(['admin/users']);
  }
}