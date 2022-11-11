import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService:UserService, private router: Router) { }

  currentUser: any = null;
  username: string = "";
  password: string = "";
  address: string = "";

  ngOnInit(): void {
    this.findUser()
  }

  async updateUser(){
    const user: User = {id:0, username: this.username, password: this.password, address: this.address};
    const gotUser: User = await this.userService.updateUser(user);
    this.address = gotUser.address;
  }

  async findUser(){
    this.currentUser = await this.userService.getCurrentUser();
    this.username = this.currentUser.username;
    this.address = this.currentUser.address;
  }

}
