import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cartproduct',
  templateUrl: './cartproduct.component.html',
  styleUrls: ['./cartproduct.component.css']
})
export class CartproductComponent implements OnInit {

  constructor(private cartService:CartService) { }

  ngOnInit(): void {
  }

  @Input() inputProduct: any = null;
  quantity: number = this.inputProduct.quantity;

  @Output()
  notice: EventEmitter<number> = new EventEmitter<number>();

  async updateItem(){
    const updateProduct: Product =
    {
      id: this.inputProduct.id,
      name: "",
      type: "",
      subtype: "",
      price: 0,
      image: "",
      stock: 0,
      rarity: "",
      cartQuant: this.quantity,
      description: ""
    };
    if(updateProduct.cartQuant === 0){
      this.notice.emit(this.inputProduct.id);
    }
    else{
      const inputProduct: Product = await this.cartService.updateCartProduct(updateProduct);
    }
  }

}
