import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Role, UserRole } from '@app/roles/role';
import { RoleService } from '@app/roles/role.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { User } from '@app/users/user';
import { UserService } from '@app/users/user.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {
  @Input('user') currentUser: User;
  @Output() addedRole = new EventEmitter<boolean>();

  form: FormGroup;
  roles: Role[];

  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private roleService: RoleService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      role: ['', Validators.required]
    });

    this.roleService.getAll()
      .pipe(first())
      .subscribe(roleVm => this.roles = roleVm.roles);
  }

  get f() { return this.form.controls; }

  save() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    let userRole: UserRole = {
      userId: this.currentUser.id,
      roleId: this.form.value.role
    };

    this.loading = true;
    this.roleService.addUserRole(userRole)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Successfully assigned role to user!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.addedRole.emit(true);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  gotoUsers() {

  }

}
