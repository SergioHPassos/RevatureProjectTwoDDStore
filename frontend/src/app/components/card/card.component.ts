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

  ngOnInit(): void {}

  testFunction(rarity: string): string {
    switch (rarity) {
      case 'Rare':
        return 'text-[#]';
        break;
    }
    return '';
  }

  addToCart = (product: Product) => {
    this.cartService.addToCart(product);
  };
}
