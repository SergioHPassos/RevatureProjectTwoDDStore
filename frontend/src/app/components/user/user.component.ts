import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

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
}
