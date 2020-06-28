import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CarCategoryService } from '@app/car-categories/car-category.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css']
})
export class CategoryDetailComponent implements OnInit {
  form: FormGroup;
  id: number;
  loading = false;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private carCategoryService: CarCategoryService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      rentalValue: ['', [Validators.required, Validators.min(200), Validators.max(2500)]]
    });

    this.route.paramMap.pipe(
      switchMap(params =>
        this.carCategoryService.get(+params.get('id'))))
      .subscribe(category => {
        this.id = category.id;
        this.f.id.setValue(category.id);
        this.f.name.setValue(category.name);
        this.f.rentalValue.setValue(category.rentalValue);
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
    this.carCategoryService.update(this.id, this.form.value)
      .pipe(first())
      .subscribe(data => {
        this.alertService.success('Update successful!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.location.back();
      },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
