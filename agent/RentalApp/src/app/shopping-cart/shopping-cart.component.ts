import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@app/auth/auth.service';
import { RentalService } from '@app/rentals/rental.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';
import { ShoppingCartItem } from './shopping-cart';
import { ShoppingCartService } from './shopping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  userId: number;
  shoppingCartItems: ShoppingCartItem[];
  isCartEmpty = false;

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
    this.shoppingCartService.getUserShoppingCart(this.userId)
      .pipe(first())
      .subscribe(shoppingCart => {
        this.shoppingCartItems = shoppingCart.items
        this.isCartEmpty = !this.shoppingCartItems || this.shoppingCartItems.length == 0;
      });
  }

  placeOrder() {
  }
}
