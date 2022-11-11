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
    const observable = this.http.get<User>("placeholder");
    const user = await firstValueFrom(observable);
    return user;
  }

  async loginUser(user: User):Promise<User>{
    const observable = this.http.get<User>("placeholder for login", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }

  async registerUser(user: User):Promise<User>{
    const observable = this.http.post<User>("placeholder for register", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }

  async updateUser(user: User):Promise<User>{
    const observable = this.http.post<User>("placeholder for update", user);
    const foundUser = await firstValueFrom(observable);
    return foundUser;
  }
}
