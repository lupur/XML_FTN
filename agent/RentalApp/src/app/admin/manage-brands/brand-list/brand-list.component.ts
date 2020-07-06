import { Component, OnInit } from '@angular/core';
import { CarBrandService } from '@app/car-brands/car-brand.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';
import { CarBrand } from '@app/car-brands/car-brand';

@Component({
  selector: 'app-brand-list',
  templateUrl: './brand-list.component.html',
  styleUrls: ['./brand-list.component.css']
})
export class BrandListComponent implements OnInit {
  carBrands: CarBrand[];

  constructor(
    private carBrandService: CarBrandService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.carBrandService.getAll()
      .pipe(first())
      .subscribe(carBrandVm => this.carBrands = carBrandVm.carBrands);
  }

  delete(name: string) {
    const carBrand = this.carBrands.find(x => x.name == name);
    carBrand.isDeleting = true;

    this.carBrandService.delete(name)
      .pipe(first())
      .subscribe(() => {
        this.carBrands = this.carBrands.filter(x => x.name !== name);
        this.alertService.success('Brand deleted successfully', {
          autoClose: true
        });
      });
  }

}
