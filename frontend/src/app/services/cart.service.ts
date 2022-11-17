import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Product } from '../models/product';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http : HttpClient) { }

  async getUserCart(user: User):Promise<Product[]>{
    const observable = this.http.post<Product[]>("http://localhost:8080/getUserCart", user);
    const products = await firstValueFrom(observable);
    return products;
  }

  async addToCart(product: Product):Promise<Product[]>{
    const observable = this.http.post<Product[]>("http://localhost:8080/addToCart", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

  async updateCartProduct(product: Product):Promise<Product>{
    const observable = this.http.post<Product>("http://localhost:8080/updateCart", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

  async deleteCartProduct(product: Product):Promise<Product[]>{
    const observable = this.http.post<Product[]>("http://localhost:8080/deleteCartProduct", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

 async checkout(user:User) {
  const observable = this.http.post("http://localhost:8080/checkout", user);
  
 }
}
