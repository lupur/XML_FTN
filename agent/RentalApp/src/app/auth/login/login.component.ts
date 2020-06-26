import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';

import { AlertService } from '@app/shared/alert/alert.service';
import { AuthService } from './../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  returnUrl: string;

  loading = false;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private alertService: AlertService
  ) {
    if (this.authService.userValue) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      usernameOrEmail: ['', Validators.required],
      password: ['', Validators.required],
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/home';
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.authService.login(this.f.usernameOrEmail.value, this.f.password.value)
      .pipe(first())
      .subscribe(_ => {
        this.router.navigate([this.returnUrl]);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
