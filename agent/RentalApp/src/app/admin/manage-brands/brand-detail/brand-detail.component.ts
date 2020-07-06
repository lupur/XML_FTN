import { Location } from '@angular/common';
import { CarModelService } from '@app/car-models/car-model.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarBrand } from '@app/car-brands/car-brand';
import { CarBrandService } from '@app/car-brands/car-brand.service';
import { CarModel } from '@app/car-models/car-model';
import { switchMap, first } from 'rxjs/operators';

@Component({
  selector: 'app-brand-detail',
  templateUrl: './brand-detail.component.html',
  styleUrls: ['./brand-detail.component.css']
})
export class BrandDetailComponent implements OnInit {
  carBrand: CarBrand;
  carModels: CarModel[];

  selectedCarBrand: string;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private carBrandService: CarBrandService,
    private carModelService: CarModelService
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(switchMap(params =>
        this.carBrandService.get(params.get('name'))))
      .subscribe(carBrand => {
        this.carBrand = carBrand;

        this.getAllBrands();
        this.getModelsByBrand();
      });
  }

  gotoBrands() {
    this.location.back();
  }

  private getAllBrands() {
    return this.carBrandService.get(this.carBrand.name)
      .pipe(first())
      .subscribe(carBrand => this.carBrand = carBrand);
  }

  private getModelsByBrand() {
    this.carModelService.getByBrand(this.carBrand.name)
      .pipe(first())
      .subscribe(carModelVm => this.carModels = carModelVm.carModels);
  }
}
