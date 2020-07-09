import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { ShoppingCart, ShoppingCartItem } from './shopping-cart';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  constructor(private http: HttpClient) { }

  getUserShoppingCart(userId: number) {
    let params = new HttpParams()
      .set('userId', userId.toString());
    return this.http.get<ShoppingCart>(`${environment.apiUrl}/shopping-cart`, { params: params });
  }

  createUserShoppingCart(shoppingCart: ShoppingCart) {
    return this.http.post(`${environment.apiUrl}/shopping-cart`, shoppingCart);
  }

  addItemToMyShoppingCart(item: ShoppingCartItem) {
    return this.http.post(`${environment.apiUrl}/shopping-cart/items/add`, item);
  }
}
