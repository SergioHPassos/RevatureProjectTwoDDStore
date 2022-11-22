import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  username: string = '';
  password: string = '';

  signInUser = () => {
    this.userService.loginUser({
      id: 0,
      username: this.username,
      password: this.password,
      address: '',
      image: '',
    });
  };
}
