import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/auth/auth.service';
import { AccountStatus } from '@app/auth/user';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users = null;

  constructor(
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.authService.getAll()
      .pipe(first())
      .subscribe(userVm => this.users = userVm.users);
  }

  delete(id: number) {
    const user = this.users.find(x => x.id == id);
    user.isDeleting = true;

    this.authService.delete(id)
      .pipe(first())
      .subscribe(() => {
        this.users = this.users.filter(x => x.id !== id);
        this.alertService.success('User successfully removed!', {
          keepAfterRouteChange: true, autoClose: true
        });
      });
  }

  switchStatus(status: string) {
    switch (status) {
      case AccountStatus.ACTIVE:
        return 'badge-success';
      case AccountStatus.PENDING:
        return 'badge-warning';
      case AccountStatus.BLOCKED:
        return 'badge-danger';
    }
  }

}
