import { AlertService } from '@app/shared/alert/alert.service';
import { CarBrandService } from './../../../car-brands/car-brand.service';
import { CarBrand } from '@app/car-brands/car-brand';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-brand-list',
  templateUrl: './brand-list.component.html',
  styleUrls: ['./brand-list.component.css']
})
export class BrandListComponent implements OnInit {
  carBrands: CarBrand[];
  deleting = false;

  constructor(
    private carBrandService: CarBrandService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.carBrandService.getAll()
      .pipe(first())
      .subscribe(carBrandVm => this.carBrands = carBrandVm.carBrands);
  }

  delete(name: string) {
    this.deleting = true;
    this.carBrandService.delete(name)
      .pipe(first())
      .subscribe(() => {
        this.carBrands = this.carBrands.filter(x => x.name !== name);
        this.alertService.success('Brand deleted successfully', {
          autoClose: true
        });
        this.deleting = false;
      });
  }

}
