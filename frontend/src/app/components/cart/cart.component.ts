import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { User } from 'src/app/models/user';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  constructor(
    private cartService: CartService,
    private userService: UserService,
    private router: Router
  ) {}

  cartList: any = null;
  currentUser: any = null;

  cart: Product[] = [];
  total: number = 0;
  user: User = {
    id: -1,
    username: '',
    password: '',
    address: '',
    image: '',
  };

  ngOnInit(): void {
    this.cartService.cartSubject.subscribe((cart) => {
      this.cart = cart;
    });
    this.cartService.total.subscribe((total) => {
      this.total = total;
    });
    this.userService.userSubject.subscribe((user) => {
      this.user = user;
    });
    this.updateTotal();
  }

  async updateTotal() {
    await this.cartService.getUserCart();
    for (let product of this.cart) {
      this.total += product.price * (product.cartAmount || 1);
    }
  }

  async checkout() {
    await this.cartService.cartCheckout(
      await this.userService.getCurrentUser()
    );
  }
}
