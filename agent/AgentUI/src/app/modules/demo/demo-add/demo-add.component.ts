import { Component, OnInit } from '@angular/core';
import { DemoService } from '../demo.service';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-demo-add',
  templateUrl: './demo-add.component.html',
  styleUrls: ['./demo-add.component.css']
})
export class DemoAddComponent implements OnInit {
  form: FormGroup;

  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private demoService: DemoService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.demoService.create(this.form.value)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Demo car brand added successfully', { keepAfterRouteChange: true, autoClose: true });
        this.router.navigate(['.', { relativeTo: this.route }]);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
