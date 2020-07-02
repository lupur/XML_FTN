import { Component, OnInit } from '@angular/core';
import { Car } from '../car';
import { CarService } from '../car.service';
import { first } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.css']
})
export class CarDetailComponent implements OnInit {
  id: number;
  car: Car;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.carService.get(this.id)
      .pipe(first())
      .subscribe(car => this.car = car);
  }

}
