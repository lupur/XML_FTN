import { Component, OnInit } from '@angular/core';
import { CarsService } from './cars.service';
import { CarVm, Car } from '@app/models/car';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {
  grid = true;
  cars: Car[];

  constructor(private carsService: CarsService) { }

  ngOnInit(): void {
    this.carsService.getAll()
      .pipe(first())
      .subscribe(carVm => this.cars = carVm.cars)
  }

  switchView(isGrid) {
    this.grid = isGrid;
  }

}
