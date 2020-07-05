import { Injectable } from '@angular/core';
import { Car } from '@app/cars/car';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private shoppingCart: Car[] = [];

  constructor() { }

  getAll() {
    return this.shoppingCart;
  }

  add(car: Car) {
    this.shoppingCart.push(car);
  }

  remove(id: number) {
    this.shoppingCart.filter(c => c.id !== id);
  }
}
