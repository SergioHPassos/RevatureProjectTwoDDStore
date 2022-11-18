import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable, Subject } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {
    this.productsSubject.subscribe((value) => {
      this.products = value;
    });
  }

  products: Product[] = [];
  productsSubject: Subject<Product[]> = new Subject<Product[]>();

  updateProducts(products: Product[]) {
    this.productsSubject.next(products);
  }

  async getAllProducts(): Promise<Product[]> {
    const observable = this.http.get<Product[]>(
      'http://localhost:8080/getProducts'
    );
    const products = await firstValueFrom(observable);
    this.updateProducts(products);
    return products;
  }

  async getProductByID(product: Product): Promise<Product> {
    const observable = this.http.post<Product>('placeholder for id', product);
    const foundProduct = await firstValueFrom(observable);
    return foundProduct;
  }

  async getProductByType(type: string): Promise<Product[]> {
    const observable = this.http.post<Product[]>(
      'http://localhost:8080/getProductsbyType',
      {
        type: type,
      }
    );
    const foundProducts = await firstValueFrom(observable);
    this.updateProducts(foundProducts);
    return foundProducts;
  }

  async getProductBySubtype(type: string, subtype: string): Promise<Product[]> {
    const observable = this.http.post<Product[]>(
      'http://localhost:8080/getProductsbyTypeAndSubtype',
      {
        type: type,
        subtype: subtype,
      }
    );
    const foundProducts = await firstValueFrom(observable);
    this.updateProducts(foundProducts);
    return foundProducts;
  }
}
