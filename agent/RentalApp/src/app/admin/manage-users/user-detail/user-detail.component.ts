import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/shared/alert/alert.service';
import { AccountStatus } from '@app/users/user';
import { UserService } from '@app/users/user.service';
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
    private userService: UserService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['id'];
    this.userService.getById(this.userId)
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

    this.userService.activate(id)
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

    this.userService.block(id)
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
