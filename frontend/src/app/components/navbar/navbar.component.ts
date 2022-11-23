import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(
    private userService: UserService,
    private _router: Router,
    private cartService: CartService
  ) {}

  currentUser: User = {
    id: -1,
    username: '',
    password: '',
    address: '',
    image: '',
  };

  ngOnInit(): void {
    this.userService.userSubject.subscribe((user) => {
      this.currentUser = user;
    });
  }

  getCurrentUser = async () => {
    await this.userService.getCurrentUser();
  };

  logoutCurrentUser = async () => {
    await this.userService.logoutUser();
    this._router.navigateByUrl('/');
  };

  updateCartTotal = () => {
    this.cartService.updateTotal();
  };
}
