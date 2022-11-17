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
    const observable = this.http.post<Product[]>("placeholder",user);
    const products = await firstValueFrom(observable);
    return products;
  }

  async addToCart(product: Product):Promise<Product[]>{
    const observable = this.http.post<Product[]>("placeholder for add", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

  async updateCartProduct(product: Product):Promise<Product>{
    const observable = this.http.post<Product>("placeholder for update", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

  async deleteCartProduct(product: Product):Promise<Product[]>{
    const observable = this.http.post<Product[]>("placeholder for delete", product);
    const gotProduct = await firstValueFrom(observable);
    return gotProduct;
  }

 async checkout(user:User) {
  const observable = this.http.post("placeholder for checkout", user);
  
 }
}
