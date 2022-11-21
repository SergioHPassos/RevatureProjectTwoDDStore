import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Subject } from 'rxjs';
import { Product } from '../models/product';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpClient) {}

  cartSubject: Subject<Product[]> = new Subject<Product[]>();

  updateCart(cart: Product[]) {
    this.cartSubject.next(cart);
  }

  async getUserCart(user: User): Promise<Product[]> {
    const observable = this.http.get<Product[]>(
      'http://localhost:8080/getUserCart'
    );
    const cart = await firstValueFrom(observable);
    this.updateCart(cart);
    return cart;
  }

  async addToCart(product: Product): Promise<Product[]> {
    const observable = this.http.post<Product[]>(
      'http://localhost:8080/addToCart',
      product
    );
    const cartUpdated = await firstValueFrom(observable);
    this.updateCart(cartUpdated);
    return cartUpdated;
  }

  async updateCartProduct(product: Product): Promise<Product> {
    const observable = this.http.post<Product>(
      'placeholder for update',
      product
    );
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

  async deleteCartProduct(product: Product): Promise<Product[]> {
    const observable = this.http.post<Product[]>(
      'http://localhost:8080/deleteCartProduct',
      product
    );
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }
}
