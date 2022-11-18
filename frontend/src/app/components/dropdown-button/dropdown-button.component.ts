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
      case 'weapons':
        return 'Weapons';
      case 'potion':
        return 'Potions';
      case 'armor':
        return 'Armor';
      case 'jewelry':
        return 'Jewelry';
      case 'gear':
        return 'Gear';
      case 'companions':
        return 'Companions';
    }

    return '';
  };

  getSubType = (type: string, subtype: string) => {
    switch (type.toLowerCase()) {
      case 'weapons':
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
          case 'great':
            return 'Great Swords';
            break;
          case 'giant only':
            return 'Giants';
            break;
          case 'longbow':
            return 'Longbows';
            break;
          case 'shortbows':
            return 'Shortbows';
            break;
          case 'axe':
            return 'Axes';
            break;
          case 'katana':
            return 'Katanas';
            break;
          case 'blunt':
            return 'Bludgeoning';
            break;
          case 'pole':
            return 'Polearms';
            break;
        }
        break;
      case 'potion':
        switch (subtype.toLowerCase()) {
          case 'healing':
            return 'Healing Potions';
            break;
          case 'transmutation':
            return 'Transmutation Potions';
            break;
          case 'abjuration':
            return 'Abjuration Potions';
            break;
          case 'mana':
            return 'Mana Potions';
            break;
          case 'dual':
            return 'Dual Potions';
            break;
        }
        break;
      case 'jewelry':
        switch (subtype.toLowerCase()) {
          case 'bangle':
            return 'Bangles';
            break;
          case 'ring':
            return 'Rings';
            break;
        }
        break;
      case 'armor':
        switch (subtype.toLowerCase()) {
          case 'head':
            return 'Headwear';
            break;
          case 'chest':
            return 'Chestpieces';
            break;
          case 'shield':
            return 'Shields';
            break;
        }
        break;
      case 'companions':
        switch (subtype.toLowerCase()) {
          case 'pets':
            return 'Pets';
            break;
        }
        break;
      case 'gear':
        switch (subtype.toLowerCase()) {
          case 'travel':
            return 'Travel';
            break;
        }
        break;
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
