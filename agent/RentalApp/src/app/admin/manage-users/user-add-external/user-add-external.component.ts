import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '@app/shared/alert/alert.service';
import { UserService } from '@app/users/user.service';
import { first } from 'rxjs/operators';
import { User } from '@app/users/user';

@Component({
  selector: 'app-user-add-external',
  templateUrl: './user-add-external.component.html',
  styleUrls: ['./user-add-external.component.css']
})
export class UserAddExternalComponent implements OnInit {
  form: FormGroup;

  username: string;
  email: string;

  loading = false;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.username = params['username'];
      this.email = params['email'];
    });
    this.form = this.formBuilder.group({
      username: [this.username, Validators.required],
      email: [this.email, [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(7)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(7)]]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    if (this.form.value.password !== this.form.value.confirmPassword) {
      this.f.confirmPassword.setErrors({ notEqual: true });
      return;
    }

    this.loading = true;
    this.userService.registerExternal(this.form.value)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('User successfully registered to RCC!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.gotoUserDetail();
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  gotoUserDetail() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}
