import { Component, OnInit } from '@angular/core';
import { CarModel } from '@app/models/car-model';
import { ActivatedRoute } from '@angular/router';
import { CarBrandsService } from '../car-brands.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-car-brands-details',
  templateUrl: './car-brands-details.component.html',
  styleUrls: ['./car-brands-details.component.css']
})
export class CarBrandsDetailsComponent implements OnInit {
  carBrandName: string;
  carModels: CarModel[];

  constructor(
    private route: ActivatedRoute,
    private carBrandsService: CarBrandsService
  ) { }

  ngOnInit(): void {
    this.carBrandName = this.route.snapshot.params['carBrandName'];
    this.carBrandsService.getById(this.carBrandName)
      .pipe(first())
      .subscribe(carBrand => this.carModels = carBrand.carModels);
  }

}
