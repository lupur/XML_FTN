import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/auth/auth.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { ShoppingCartItem } from '@app/shopping-cart/shopping-cart';
import { ShoppingCartService } from '@app/shopping-cart/shopping-cart.service';
import { User } from '@app/users/user';
import { first } from 'rxjs/operators';
import { CarService } from '../car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  currentUser: User;

  loading = false;
  cars = null;
  grid = true;

  constructor(
    private carService: CarService,
    private authService: AuthService,
    private shoppingCartService: ShoppingCartService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authService.userValue;
    this.carService.getAll()
      .pipe(first())
      .subscribe(carVm => this.cars = carVm.cars);
  }

  switchView(isGrid) {
    this.grid = isGrid;
  }

  addToCart(carId: number) {
    let item: ShoppingCartItem = {
      carId: carId,
      shoppingCartId: this.currentUser.shoppingCartId
    }

    this.loading = true;
    this.shoppingCartService.addItemToMyShoppingCart(item)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Item added to cart!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }
}
