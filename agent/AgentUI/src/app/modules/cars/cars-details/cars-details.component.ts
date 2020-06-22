import { Component, OnInit, Input } from '@angular/core';
import { Car } from '@app/models/car';
import { CarsService } from '../cars.service';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-cars-details',
  templateUrl: './cars-details.component.html',
  styleUrls: ['./cars-details.component.css']
})
export class CarsDetailsComponent implements OnInit {
  id: number;
  car: Car;

  constructor(
    private route: ActivatedRoute,
    private carsService: CarsService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.carsService.getById(this.id)
      .pipe(first())
      .subscribe(car => this.car = car);
  }

}
