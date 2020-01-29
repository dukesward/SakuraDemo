import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SakuraApiManager } from './sakura.api.manager';

@Injectable({
  providedIn: 'root'
})
export class AuthApiManager extends SakuraApiManager {

  constructor(protected httpClient: HttpClient) {
    super(httpClient);
    this.apiKey = 'auth';
  }

  doLogin() {
    return this.header('Authorization', 'abcd').doPost({}, 'login', { username: 'abc' });
  }

  doRegister(postData) {
    return this.header('Authorization', 'abcd').doPost({}, 'register', postData);
  }
}
