import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, firstValueFrom, Subject } from 'rxjs';
import { Product } from '../models/product';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpClient) {}

  cartSubject: Subject<Product[]> = new Subject<Product[]>();
  total: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  updateCart(cart: Product[]) {
    this.cartSubject.next(cart);
  }

  updateTotalSubject(gold: number) {
    this.total.next(gold);
  }

  async getUserCart(): Promise<Product[]> {
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
      'http://localhost:8080/updateCart',
      product
    );
    const gotProduct = await firstValueFrom(observable);
    await this.getUserCart();
    return gotProduct;
  }

  async deleteCartProduct(product: Product): Promise<Product[]> {
    try {
      const observable = this.http.post<Product[]>(
        'http://localhost:8080/deleteCartProduct',
        product
      );
      const gotProduct = await firstValueFrom(observable);
      this.getUserCart();
      return gotProduct;
    } catch (error) {
      return Promise.reject(error);
    }
  }

  async cartCheckout(user: User): Promise<void> {
    try {
      const observable = await this.http.post<Product[]>(
        'http://localhost:8080/checkout',
        {}
      );
      const res = await firstValueFrom(observable);
      console.log(res);
    } catch (error) {
      return Promise.reject(error);
    }
  }

  async updateTotal() {
    const cart: Product[] = await this.getUserCart();
    let total: number = 0;
    for (let product of cart) {
      console.log(product);
      total += product.price * (product.cartAmount || 1);
    }
    this.updateTotalSubject(total);
  }
}
