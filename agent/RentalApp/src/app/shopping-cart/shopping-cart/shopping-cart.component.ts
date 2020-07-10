import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@app/auth/auth.service';
import { RentalService } from '@app/rentals/rental.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';
import { ShoppingCartService } from '../shopping-cart.service';

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

  placeOrder() {
  }

  removeFromCart(id: number) {
    const item = this.shoppingCartItems.filter(x => x.id !== id);
    item.isRemoving = true;

    this.shoppingCartService.removeItemFromShoppingCart(id)
      .pipe(first())
      .subscribe(() => {
        this.shoppingCartItems = this.shoppingCartItems.filter(x => x.id !== id);
        this.checkIsEmpty();
        this.alertService.success('Item removed.', {
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
        this.alertService.success('Shopping cart emptied!', {
          keepAfterRouteChange: true, autoClose: true
        });
      });
  }

  private checkIsEmpty() {
    this.isCartEmpty = !this.shoppingCartItems || this.shoppingCartItems.length == 0;
  }
}
