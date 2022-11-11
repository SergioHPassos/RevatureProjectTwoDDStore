import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private productService:ProductService, private router: Router) { }

  productID: number = 0;
  savedProduct: any = null;

  ngOnInit(): void {

  }

  async getProduct(id:number){
    const product: Product = {
      id: this.productID,
      name: "",
      type: "",
      subtype: "",
      price: 0,
      image: "",
      stock: 0,
      rarity: "",
      cartQuant: 0,
      description: ""
    };
    const gotProduct: Product = await this.productService.getProductByID(product);
  }

}
