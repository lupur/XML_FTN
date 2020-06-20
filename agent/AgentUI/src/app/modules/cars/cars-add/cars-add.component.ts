import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { CarsService } from '../cars.service';
import { first } from 'rxjs/operators';
import { CarCategory } from '@app/models/car-category';

@Component({
  selector: 'app-cars-add',
  templateUrl: './cars-add.component.html',
  styleUrls: ['./cars-add.component.css']
})
export class CarsAddComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;
  brands = ['BMW', 'Mercedes', 'Audi'];
  carCategories: CarCategory[];
  selectedCarCategory: CarCategory;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private carsService: CarsService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      brand: ['', Validators.required],
      carCategory: ['', Validators.required]
    })

    this.carsService.getCarCategories()
      .pipe(first())
      .subscribe(carCategoryVm =>
        this.carCategories = carCategoryVm.carCategories);
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.carsService.create(this.form.value)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Car added successfully', { keepAfterRouteChange: true, autoClose: true });
        this.router.navigate(['.', { relativeTo: this.route }]);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
