import { CarBrandService } from './../../../car-brands/car-brand.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CarBrand } from '@app/car-brands/car-brand';
import { Component, OnInit } from '@angular/core';
import { switchMap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-brand-detail',
  templateUrl: './brand-detail.component.html',
  styleUrls: ['./brand-detail.component.css']
})
export class BrandDetailComponent implements OnInit {
  carBrand: CarBrand;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private carBrandService: CarBrandService
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(switchMap(params =>
        this.carBrandService.get(params.get('name')))
      )
      .subscribe(carBrand =>
        this.carBrand = carBrand);
  }
}
