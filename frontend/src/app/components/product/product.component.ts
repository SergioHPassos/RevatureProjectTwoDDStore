import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private productService:ProductService, private cartService:CartService, private router: Router, private activatedRoute:ActivatedRoute) { }

  productID: any = this.activatedRoute.snapshot.paramMap.get('id');
  savedProduct: any = null;
  relatedProducts: Product[] = [];
  imageURL: string = '';
  ngOnInit(): void {
    if(this.productID !== null){
      (async () => {
        const product: Product = {
          id: parseInt(this.productID),
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
        this.savedProduct = gotProduct;
        let base64 = Buffer.from(this.savedProduct.image, 'base64')
        this.imageURL = 'data:image/jpeg;base64,' + base64;
        this.relatedProducts = await this.productService.getProductByType(this.savedProduct);
      })
    }
  }

  async addProductToCart(){
    this.cartService.addToCart(this.savedProduct);
  }

}
