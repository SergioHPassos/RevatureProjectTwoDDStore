import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-logreg',
  templateUrl: './logreg.component.html',
  styleUrls: ['./logreg.component.css']
})
export class LogregComponent implements OnInit {

  constructor(private userService:UserService, private router: Router) { }
  
  usernameIn: string = "";
  passwordIn: string = "";
  usernameSaved: string = "";
  //true = register page, false = login page
  registerMode: boolean = false;

  ngOnInit(): void {
  }

  async login(){
    const user: User = {id:0,username:this.usernameIn, password:this.passwordIn,address:""};
    const gotUser: User = await this.userService.loginUser(user);
    this.usernameSaved = gotUser.username;

    setTimeout( () => this.router.navigateByUrl("/user"), 2000);
  }

  async register(){
    const user: User = {id:0,username:this.usernameIn, password:this.passwordIn,address:""};
    const gotUser: User = await this.userService.registerUser(user);
    this.usernameSaved = gotUser.username;

    setTimeout( () => this.router.navigateByUrl("/user"), 2000);
  }

}
