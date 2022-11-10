import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http : HttpClient) { }

  async getAllProducts():Promise<Product[]>{
    const observable = this.http.get<Product[]>("placeholder");
    const products = await firstValueFrom(observable);
    return products;
  }

  async getProductByID(product: Product):Promise<Product>{
    const observable = this.http.post<Product>("placeholder for id", product);
    const foundProduct = await firstValueFrom(observable);
    return foundProduct;
  }

  async getProductByType(product: Product):Promise<Product>{
    const observable = this.http.post<Product>("placeholder for type", product);
    const foundProduct = await firstValueFrom(observable);
    return foundProduct;
  }
}
