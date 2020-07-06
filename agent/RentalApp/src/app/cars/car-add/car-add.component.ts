import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CarBrand } from '@app/car-brands/car-brand';
import { CarBrandService } from '@app/car-brands/car-brand.service';
import { CarCategoryService } from '@app/car-categories/car-category.service';
import { CarModelService } from '@app/car-models/car-model.service';
import { FuelType, TransmissionType } from '@app/cars/car';
import { CarService } from '@app/cars/car.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-car-add',
  templateUrl: './car-add.component.html',
  styleUrls: ['./car-add.component.css']
})
export class CarAddComponent implements OnInit {
  form: FormGroup;

  carCategories = null;
  carBrands = null;
  carModels = null;

  fuelTypes = [
    FuelType[FuelType.Gasoline],
    FuelType[FuelType.Diesel],
    FuelType[FuelType.Electric],
    FuelType[FuelType.Hybrid]
  ];
  transmissionTypes = [
    TransmissionType[TransmissionType.Manual],
    TransmissionType[TransmissionType.Automatic],
    TransmissionType[TransmissionType.SemiAutomatic]
  ];

  loading = false;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private carCategoryService: CarCategoryService,
    private carBrandService: CarBrandService,
    private carModelService: CarModelService,
    private carService: CarService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      carCategory: ['', Validators.required],
      carBrand: ['', Validators.required],
      carModel: ['', Validators.required],
      productionYear: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]],
      fuelType: ['', Validators.required],
      transmissionType: ['', Validators.required],
      color: ['', Validators.required],
      location: ['', Validators.required],
      mileage: ['', [Validators.required, Validators.min(0)]],
      mileageConstraint: ['', Validators.max(10000)],
      numberOfSeats: ['', [Validators.required, Validators.min(2), Validators.max(7)]]
    });

    this.getCarCategories();
    this.getCarBrands();
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.carService.create(this.form.value)
      .pipe(first())
      .subscribe(
        result => {
          this.alertService.success(`New car added successfully!`, {
            keepAfterRouteChange: true, autoClose: true
          });
          this.router.navigate(['../'], {
            relativeTo: this.route
          });
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

  onSelectedCarBrand(carBrand: string) {
    this.getCarModels(carBrand);
  }

  private getCarCategories() {
    this.carCategoryService.getAll()
      .pipe(first())
      .subscribe(result => this.carCategories = result.carCategories);
  }
  private getCarBrands() {
    this.carBrandService.getAll()
      .pipe(first())
      .subscribe(result => this.carBrands = result.carBrands);
  }
  private getCarModels(brand: string) {
    this.carModelService.getByBrand(brand)
      .pipe(first())
      .subscribe(result => this.carModels = result.carModels);
  }
}
