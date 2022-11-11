import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-front',
  templateUrl: './front.component.html',
  styleUrls: ['./front.component.css']
})
export class FrontComponent implements OnInit {

  constructor(private productService: ProductService, private router: Router) { }

  products: Product[] = [];

  ngOnInit(): void {
    (async () => {
      this.products = await this.productService.getAllProducts();
    })
  }

}
