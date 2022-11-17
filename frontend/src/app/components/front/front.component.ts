import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-front',
  templateUrl: './front.component.html',
  styleUrls: ['./front.component.css']
})
export class FrontComponent implements OnInit {

  constructor(private productService: ProductService, private router: Router, private activatedRoute:ActivatedRoute) { }

  products: Product[] = [];
  requestedType: any = this.activatedRoute.snapshot.paramMap.get('type');
  requestedSubtype: any = this.activatedRoute.snapshot.paramMap.get('subtype');

  ngOnInit(): void {
    (async () => {
      if(this.requestedSubtype !== null){
        const product: Product = {
          id: 0,
          name: "",
          type: this.requestedType,
          subtype: this.requestedSubtype,
          price: 0,
          image: "",
          stock: 0,
          rarity: "",
          cartQuant: 0,
          description: ""
        };
        this.products = await this.productService.getProductBySubtype(product);
      }
      else if(this.requestedType !== null){
        const product: Product = {
          id: 0,
          name: "",
          type: this.requestedType,
          subtype: this.requestedSubtype,
          price: 0,
          image: "",
          stock: 0,
          rarity: "",
          cartQuant: 0,
          description: ""
        };
        this.products = await this.productService.getProductByType(product);
      }
      else{
        this.products = await this.productService.getAllProducts();
      }
    })
  }

}
