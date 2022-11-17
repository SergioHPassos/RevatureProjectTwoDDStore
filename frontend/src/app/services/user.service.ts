import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  async getCurrentUser():Promise<User>{
    const observable = this.http.get<User>("http://localhost:8080/getCurrentUser");
    const user = await firstValueFrom(observable);
    return user;
  }

  async loginUser(user: User):Promise<User>{
    const observable = this.http.post<User>("http://localhost:8080/logInUser", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }

  async registerUser(user: User):Promise<User>{
    const observable = this.http.post<User>("http://localhost:8080/registerUser", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }

  async updateUser(user: User):Promise<User>{
    const observable = this.http.post<User>("http://localhost:8080/updateUser", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }

  async logoutUser(){
    const observable = this.http.get<string>("placeholder for logout");
  }
}
