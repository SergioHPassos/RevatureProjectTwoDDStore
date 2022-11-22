import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Subject } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  userSubject: Subject<User> = new Subject<User>();

  updateCurrentUser = (user: User) => {
    this.userSubject.next(user);
  };

  async getCurrentUser(): Promise<User> {
    try {
      const observable = this.http.get<User>(
        'http://localhost:8080/getCurrentUser'
      );
      const user = await firstValueFrom(observable);
      this.updateCurrentUser(user);
      return user;
    } catch (error) {
      const emptyUser: User = {
        id: -1,
        username: '',
        password: '',
        address: '',
        image: '',
      };
      this.updateCurrentUser(emptyUser);
      return emptyUser;
    }
  }

  async loginUser(user: User): Promise<User> {
    const observable = this.http.post<User>('http://localhost:8080/logInUser', {
      username: user.username,
      password: user.password,
    });
    const foundUser = await firstValueFrom(observable);
    this.updateCurrentUser(user);
    return foundUser;
  }

  async registerUser(user: User): Promise<User> {
    const observable = this.http.post<User>(
      'http://localhost:8080/registerUser',
      user
    );
    const foundUser = await firstValueFrom(observable);
    await this.logoutUser();
    console.log(foundUser);
    await this.loginUser(foundUser);
    return foundUser;
  }

  async updateUser(user: User): Promise<User> {
    const observable = this.http.post<User>('placeholder for update', user);
    const foundUser = await firstValueFrom(observable);
    this.updateCurrentUser(user);
    return foundUser;
  }

  async logoutUser(): Promise<void> {
    try {
      const observable = this.http
        .get('http://localhost:8080/logout')
        .subscribe();
      const emptyUser: User = {
        id: -1,
        username: '',
        password: '',
        address: '',
        image: '',
      };
      this.updateCurrentUser(emptyUser);
    } catch (error) {}
  }
}
