import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService{
  private api = 'http://localhost:8080/api/auth';
  private TOKEN_KEY = 'jwt_token';
  constructor(private http: HttpClient) {}

  register(username: string, email: string, password: string){
      return this.http.post(`${this.api}/register`,{
        username,
        email,
        password
      }, { responseType: 'text' }).pipe(tap(token => this.saveToken(token)));
    }

    login(email: string, password: string){
      return this.http.post(`${this.api}/login`,{
        email,
        password
      }, { responseType: 'text' }).pipe(tap(token => this.saveToken(token)));
    }

  saveToken(token: string){
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null{
    return localStorage.getItem(this.TOKEN_KEY);
  }

  logout(){
    localStorage.removeItem(this.TOKEN_KEY);
  }

  isLoggedIn(): boolean{
    return !!this.getToken();
  }
}
