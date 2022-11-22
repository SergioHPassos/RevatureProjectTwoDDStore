import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input() product: Product | null = null;

  constructor(
    private sanitizer: DomSanitizer,
    private cartService: CartService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  rarityFunction(rarity: string): string {
    switch (rarity) {
      case 'Rare':
        return 'text-[#2f70a7] border-[#2f70a7]';
        break;
      case 'Common':
        return 'text-[#333333] border-[#333333]';
        break;
      case 'Legendary':
        return 'text-[#b1880a] border-[#b1880a]';
        break;
      case 'Uncommon':
        return 'text-[#328023] border-[#328023]';
        break;
      default:
        return 'text-[#333333] border-[#333333]';
        break;
    }
    return '';
  }

  addToCart = async (product: Product) => {
    if ((await this.userService.getCurrentUser()).username.length > 0) {
      this.cartService.addToCart(product);
    } else {
      this.router.navigate(['/login']);
    }
  };
}
