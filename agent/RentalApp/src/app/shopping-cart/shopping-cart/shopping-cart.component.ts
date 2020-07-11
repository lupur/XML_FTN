import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@app/auth/auth.service';
import { BundleRequest } from '@app/rentals/rental';
import { RentalService } from '@app/rentals/rental.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { User } from '@app/users/user';
import { UserService } from '@app/users/user.service';
import { first } from 'rxjs/operators';
import { ShoppingCartItem } from '../shopping-cart';
import { ShoppingCartService } from '../shopping-cart.service';
import { CarService } from '@app/cars/car.service';
import { Car } from '@app/cars/car';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  userId: number;
  shoppingCartId: number;
  shoppingCartItems = null;

  isCartEmpty = true;
  loading = false;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private shoppingCartService: ShoppingCartService,
    private authService: AuthService,
    private rentalService: RentalService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.userId = this.authService.userValue.id;
    this.getUserShoppingCart();
  }

  getUserShoppingCart() {
    this.loading = true;
    this.shoppingCartService.getUserShoppingCart(this.userId)
      .pipe(first())
      .subscribe(shoppingCart => {
        this.shoppingCartItems = shoppingCart.items
        this.shoppingCartId = shoppingCart.id;
        this.checkIsEmpty();
        this.loading = false;
      });
  }

  markItemAsBundle(id: number) {
    const item = this.shoppingCartItems.find(x => x.id === id);
    return item.isBundle = !item.isBundle;
  }

  checkout() {
    this.submitted = true;

    let request: BundleRequest = {
      rentals: []
    };
    this.shoppingCartItems.forEach((sci: ShoppingCartItem) => {
      request.rentals.push({
        carId: sci.carId,
        ownerId: sci.ownerId,
        customerId: this.userId,
        isBundle: sci.isBundle
      })
    });

    this.rentalService.createRentalRequest(request)
      .pipe(first())
      .subscribe(_ => {
        this.updateShoppingCart();
      }, error => {
        this.alertService.error(error);
        this.submitted = false;
      });
  }

  removeFromCart(id: number) {
    const item = this.shoppingCartItems.filter(x => x.id !== id);
    item.isRemoving = true;

    this.shoppingCartService.removeItemFromShoppingCart(id)
      .pipe(first())
      .subscribe(() => {
        this.shoppingCartItems = this.shoppingCartItems.filter(x => x.id !== id);
        this.checkIsEmpty();
        this.alertService.info('Item removed.', {
          keepAfterRouteChange: true, autoClose: true
        });
      });
  }

  emptyShoppingCart() {
    this.shoppingCartService.emptyShoppingCart(this.shoppingCartId)
      .pipe(first())
      .subscribe(() => {
        this.shoppingCartItems = null;
        this.checkIsEmpty();
        this.alertService.info('Shopping cart emptied!', {
          keepAfterRouteChange: true, autoClose: true
        });
      });
  }

  gotoCars() {
    this.router.navigate(['cars']);
  }

  private updateShoppingCart() {
    this.shoppingCartService.updateShoppingCart(this.shoppingCartId, { id: this.shoppingCartId })
      .pipe(first())
      .subscribe(() => {
        this.alertService.success('Order request sent.', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.gotoCars();
      })
  }

  private checkIsEmpty() {
    this.isCartEmpty = !this.shoppingCartItems || this.shoppingCartItems.length == 0;
  }
}
