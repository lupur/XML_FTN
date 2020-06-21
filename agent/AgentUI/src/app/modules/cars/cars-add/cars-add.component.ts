import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { CarsService } from '../cars.service';
import { first } from 'rxjs/operators';
import { CarCategory } from '@app/models/car-category';
import { CarModel } from '@app/models/car-model';
import { CarBrand } from '@app/models/car-brand';
import { FuelType } from '@app/models/car';

@Component({
  selector: 'app-cars-add',
  templateUrl: './cars-add.component.html',
  styleUrls: ['./cars-add.component.css']
})
export class CarsAddComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;

  selectedCarBrand: CarBrand;
  carCategories: CarCategory[];
  carBrands: CarBrand[];
  carModels: CarModel[];

  fuelTypes: string[] = [
    FuelType[FuelType.Gasoline],
    FuelType[FuelType.Diesel],
    FuelType[FuelType.Hybrid],
    FuelType[FuelType.Electric]
  ]

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private carsService: CarsService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      carCategory: ['', Validators.required],
      carBrand: ['', Validators.required],
      carModel: ['', Validators.required],
      productionYear: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]],
      fuelType: ['', Validators.required]
    })

    this.carsService.getCarCategories()
      .pipe(first())
      .subscribe(carCategoryVm =>
        this.carCategories = carCategoryVm.carCategories);

    this.carsService.getCarBrands()
      .pipe(first())
      .subscribe(carBrandVm => {
        this.carBrands = carBrandVm.carBrands
      });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.form.value.carBrand = this.selectedCarBrand.name;

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

  onSelectedCarBrand(carBrand: CarBrand) {
    this.selectedCarBrand = carBrand;
    this.carModels = this.selectedCarBrand.carModels;
  }

}
