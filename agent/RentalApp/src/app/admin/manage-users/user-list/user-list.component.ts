import { Component, OnInit } from '@angular/core';
import { AlertService } from '@app/shared/alert/alert.service';
import { UserService } from '@app/users/user.service';
import { first } from 'rxjs/operators';
import { AccountStatus } from '@app/users/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users = null;

  constructor(
    private userService: UserService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.userService.getAll()
      .pipe(first())
      .subscribe(userVm => this.users = userVm.users);
  }

  delete(id: number) {
    const user = this.users.find(x => x.id === id);
    user.isDeleting = true;

    this.userService.delete(id)
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
