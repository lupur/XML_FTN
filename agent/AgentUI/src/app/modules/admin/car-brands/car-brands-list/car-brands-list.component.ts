import { Component, OnInit } from '@angular/core';
import { CarBrand } from '@app/models/car-brand';
import { CarBrandsService } from '../car-brands.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-car-brands-list',
  templateUrl: './car-brands-list.component.html',
  styleUrls: ['./car-brands-list.component.css']
})
export class CarBrandsListComponent implements OnInit {
  carBrands: CarBrand[];

  constructor(private carBrandsService: CarBrandsService) { }

  ngOnInit(): void {
    this.carBrandsService.getAll()
      .pipe(first())
      .subscribe(carBrandVm => this.carBrands = carBrandVm.carBrands);
  }

}
