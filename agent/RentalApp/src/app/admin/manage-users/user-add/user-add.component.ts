import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RoleService } from '@app/roles/role.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { AccountStatus } from '@app/users/user';
import { UserService } from '@app/users/user.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {
  form: FormGroup;

  loading = false;
  submitted = false;
  roles = null;
  accountStatuses: string[] = [
    AccountStatus.PENDING,
    AccountStatus.ACTIVE,
    AccountStatus.BLOCKED
  ];

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private roleService: RoleService,
    private userService: UserService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.roleService.getAll()
      .pipe(first())
      .subscribe(roleVm => this.roles = roleVm.roles);
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.userService.invite(this.form.value)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('User successfully registered!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.gotoUsers();
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  gotoUsers() {
    this.router.navigate(['admin/users']);
  }

  private initializeForm() {
    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(7)]],
      status: ['', Validators.required],
      roles: ['', Validators.required]
    });
  }

}
