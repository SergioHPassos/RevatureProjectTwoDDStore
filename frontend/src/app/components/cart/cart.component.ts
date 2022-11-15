import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private cartService:CartService, private userService:UserService, private router: Router) { }

  cartList: any = null;
  currentUser: any = null;
  
  ngOnInit(): void {
   (async () => {
    this.currentUser = await this.userService.getCurrentUser();
   });
   this.getCart();
  }

  async getCart(){
    const gotCart: Product[] = await this.cartService.getUserCart(this.currentUser);
    this.cartList = gotCart;
  }

  async removeItem(event: any){
    const removeProduct: Product =
    {
      id: event,
      name: "",
      type: "",
      subtype: "",
      price: 0,
      image: "",
      stock: 0,
      rarity: "",
      cartQuant: 0,
      description: ""
    };
    const gotCart: Product[] = await this.cartService.deleteCartProduct(removeProduct);
    this.cartList = gotCart;
  }

}
