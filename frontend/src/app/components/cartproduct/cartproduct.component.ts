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

  ngOnInit(): void {
    this.cartService.cartSubject.subscribe((cart) => (this.cart = cart));
  }

  @Input() product: any = null;

  quantity: number = 1;
  cart: Product[] = [];

  updateQuantity = async (quantity: number) => {
    // before update
    if (this.product.cartAmount + quantity <= 0) {
      await this.removeProduct(this.product);
    } else {
      await this.updateCartProductQuantity(
        this.product.cartAmount === 0 ? quantity + 1 : quantity
      );
    }
    this.cartService.updateTotal();
  };

  // update quantity enum
  QUANTITY = {
    INCREMENT: 1,
    DECREMENT: -1,
  };

  removeProduct = async (product: Product) => {
    await this.cartService.deleteCartProduct(product);
    await this.cartService.getUserCart();
  };

  updateCartProductQuantity = async (quantity: number) => {
    const updatedProduct: Product = {
      ...this.product,
      cartAmount: this.product.cartAmount + quantity,
    };
    await this.cartService.updateCartProduct(updatedProduct);
  };
}
