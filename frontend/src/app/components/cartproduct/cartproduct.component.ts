import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cartproduct',
  templateUrl: './cartproduct.component.html',
  styleUrls: ['./cartproduct.component.css'],
})
export class CartproductComponent implements OnInit {
  constructor(
    private cartService: CartService,
    private userService: UserService
  ) {}

  ngOnInit(): void {}

  @Input() product: any = null;

  quantity: number = 1;

  updateQuantity = (quantity: number) => {
    // before update
    if (this.quantity + quantity) {
    }

    this.quantity += quantity;
  };

  // update quantity enum
  QUANTITY = {
    INCREMENT: 1,
    DECREMENT: -1,
  };

  removeProduct = async (product: Product) => {
    await this.cartService.deleteCartProduct(product);
    await this.cartService.getUserCart(await this.userService.getCurrentUser());
  };
}
