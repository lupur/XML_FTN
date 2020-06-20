import { Component, OnInit } from '@angular/core';
import { Car } from '@app/models/car';
import { first } from 'rxjs/operators';
import { CarsService } from '../cars.service';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.css']
})
export class CarsListComponent implements OnInit {
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
