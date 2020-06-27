import { AlertService } from '@app/shared/alert/alert.service';
import { CarCategoryService } from './../../../car-categories/car-category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent implements OnInit {
  form: FormGroup;

  loading = false;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private carCategoryService: CarCategoryService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required]],
      rentalValue: ['', [Validators.required, Validators.min(200), Validators.max(2500)]]
    })
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.carCategoryService.create(this.form.value)
      .pipe(first())
      .subscribe(data => {
        this.alertService.success('Car Category added successfully', {
          keepAfterRouteChange: true, autoClose:true
        });
        this.router.navigate(['../'], { relativeTo: this.route });
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
