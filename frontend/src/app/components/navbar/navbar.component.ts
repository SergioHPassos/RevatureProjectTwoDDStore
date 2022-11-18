import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(private userService: UserService) {}

  currentUser: User = {
    id: -1,
    username: '',
    password: '',
    address: '',
  };

  ngOnInit(): void {
    this.userService.userSubject.subscribe((user) => {
      this.currentUser = user;
    });

    this.userService.getCurrentUser();
  }

  getCurrentUser = () => {
    this.userService.getCurrentUser();
  };

  logoutCurrentUser = () => {
    this.userService.logoutUser();
  };
}
