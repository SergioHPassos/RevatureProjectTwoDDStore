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

  getType = (type: string) => {
    switch (type.toLowerCase()) {
      case 'sword':
        return 'Swords';
      case 'potion':
        return 'Potions';
      case 'shield':
        return 'Shields';
    }

    return '';
  };

  getSubType = (type: string, subtype: string) => {
    switch (type.toLowerCase()) {
      case 'sword':
        switch (subtype.toLowerCase()) {
          case 'dagger':
            return 'Daggers';
            break;
          case 'short':
            return 'Shortswords';
            break;
          case 'long':
            return 'Longswords';
            break;
          case 'rapier':
            return 'Rapiers';
            break;
          case 'two-handed':
            return 'Two-Hands';
            break;
          case 'giant only':
            return 'Giants';
            break;
        }
        break;
      case 'potion':
        switch (subtype.toLowerCase()) {
          case 'healing':
            return 'Healing Potions';
            break;
          case 'mana':
            return 'Mana Potions';
            break;
          case 'buff':
            return 'Buff Potions';
            break;
        }
        break;
      case 'shield':
        return 'Shields';
    }

    return '';
  };

  getProductTypes = (type: string) => {
    this.productServices.getProductByType(this.getType(type));
  };

  getProductSubTypes = (type: string, subtype: string) => {
    this.productServices.getProductBySubtype(
      this.getType(type),
      this.getSubType(type, subtype)
    );
  };
}
