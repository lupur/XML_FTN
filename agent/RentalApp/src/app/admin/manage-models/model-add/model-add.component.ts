import { first } from 'rxjs/operators';
import { AlertService } from '@app/shared/alert/alert.service';
import { CarModelService } from '@app/car-models/car-model.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-model-add',
  templateUrl: './model-add.component.html',
  styleUrls: ['./model-add.component.css']
})
export class ModelAddComponent implements OnInit {
  form: FormGroup;
  selectedBrandName: string;

  loading = false;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private carModelService: CarModelService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.route.parent.paramMap.subscribe(params =>
      this.selectedBrandName = params.get('name')
    );

    this.form = this.formBuilder.group({
      name: ['', [Validators.required]],
      brandName: [`${this.selectedBrandName}`]
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
    this.carModelService.create(this.form.value)
      .pipe(first())
      .subscribe(
        _ => {
          this.alertService.success('Model added successfully', {
            keepAfterRouteChange: true, autoClose: true
          });
          this.router.navigate(['../'], { relativeTo: this.route });
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
