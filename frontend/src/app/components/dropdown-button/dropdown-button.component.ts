import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-dropdown-button',
  templateUrl: './dropdown-button.component.html',
  styleUrls: ['./dropdown-button.component.css'],
})
export class DropdownButtonComponent implements OnInit {
  isDropped = 'hidden';
  @Input() type: string = '';
  @Input() subtypes: string[] = [];

  constructor(private productServices: ProductService) {}

  ngOnInit(): void {}

  toggleIsDropped(): void {
    this.isDropped = this.isDropped === 'hidden' ? '' : 'hidden';
  }

  getProductTypes = (type: string) => {
    this.productServices.getProductByType(type);
  };

  getProductSubTypes = (type: string, subtype: string) => {
    this.productServices.getProductBySubtype(type, subtype);
  };
}
