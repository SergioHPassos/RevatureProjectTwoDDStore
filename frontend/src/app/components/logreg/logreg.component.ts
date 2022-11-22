import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-logreg',
  templateUrl: './logreg.component.html',
  styleUrls: ['./logreg.component.css'],
})
export class LogregComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  username: string = '';
  password: string = '';
  matchingPassword: string = '';

  signInUser = () => {
    this.userService.loginUser({
      id: 0,
      username: this.username,
      password: this.password,
      address: '',
      image: '',
    });
  };

  registerUser = () => {
    this.userService.registerUser({
      id: 0,
      username: this.username,
      password: this.password,
      address: '',
      image:
        'https://cdn.discordapp.com/attachments/620714464609566753/1044372855246303293/token_2_34.png',
    });
  };
}
