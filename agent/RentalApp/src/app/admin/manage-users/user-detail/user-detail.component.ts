import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleType } from '@app/roles/role';
import { AlertService } from '@app/shared/alert/alert.service';
import { AccountStatus, User } from '@app/users/user';
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
    this.getUser(this.userId);
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

  switchClass(roleName: string) {
    switch (roleName) {
      case RoleType.Admin: return 'badge-danger';
      case RoleType.Agent: return 'badge-warning';
      case RoleType.Customer: return 'badge-primray';
    }
  }

  onAddedRole(addedRole: boolean) {
    this.getUser(this.userId);
  }

  private getUser(id: number) {
    this.userService.getById(id)
      .pipe(first())
      .subscribe(user => this.user = user);
  }
}
