import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';
import { first } from 'rxjs/operators';
import { Car } from '../car';
import { ShoppingCartService } from '@app/shopping-cart/shopping-cart.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars = null;
  grid = true;

  constructor(
    private carService: CarService,
    private shoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.carService.getAll()
      .pipe(first())
      .subscribe(carVm => this.cars = carVm.cars);
  }

  switchView(isGrid) {
    this.grid = isGrid;
  }

  addToCart(car: Car) {
    this.shoppingCartService.add(car);
  }
}
