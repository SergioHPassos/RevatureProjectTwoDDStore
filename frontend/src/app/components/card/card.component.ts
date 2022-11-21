import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input() product: Product | null = null;

  constructor(
    private sanitizer: DomSanitizer,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    console.log(this.product!.image ? this.product!.image : '');
  }

  rarityFunction(rarity: string): string {
    switch (rarity) {
      case 'Rare':
        return 'text-[#2f70a7]'
        break;
      case 'Common':
        return 'text-[#333333]'
        break;
      case 'Legendary':
        return 'text-[#b1880a]'
        break;
      case 'Uncommon':
        return 'text-[#328023]'
        break;
      default:
        return 'text-[#333333]'
        break;
    }
    return ''
  }

  addToCart = (product: Product) => {
    this.cartService.addToCart(product);
  };
}
