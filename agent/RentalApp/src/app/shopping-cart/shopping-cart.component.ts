import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from './shopping-cart.service';
import { Car } from '@app/cars/car';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  shoppingCart: Car[];

  constructor(private shoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.shoppingCart = this.shoppingCartService.getAll();
  }

}
