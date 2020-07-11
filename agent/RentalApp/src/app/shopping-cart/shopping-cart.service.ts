import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { ShoppingCart, ShoppingCartItem, UpdateShoppingCart } from './shopping-cart';

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

  emptyShoppingCart(id: number) {
    return this.http.delete(`${environment.apiUrl}/shopping-cart/${id}`);
  }

  addItemToMyShoppingCart(item: ShoppingCartItem) {
    return this.http.post(`${environment.apiUrl}/shopping-cart/items`, item);
  }

  removeItemFromShoppingCart(id: number) {
    return this.http.delete(`${environment.apiUrl}/shopping-cart/items/${id}`);
  }

  updateShoppingCart(id: number, command: UpdateShoppingCart) {
    return this.http.put(`${environment.apiUrl}/shopping-cart/${id}`, command);
  }
}
