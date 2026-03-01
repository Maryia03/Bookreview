import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class Auth{
  private api = environment.apiUrl;
  constructor(private http: HttpClient){}
  login(data: {email: string, password: string}){
    return this.http.post<any>(`${this.api}/auth/login`, data)
      .pipe(
        tap(response =>{
          localStorage.setItem('token', response.token);
        })
      );
  }

  register(data: {email: string, username: string, password: string}){
    return this.http.post(`${this.api}/auth/register`, data);
  }

  getToken(): string | null{
    return localStorage.getItem('token');
  }

  logout(){
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean{
    return !!this.getToken();
  }
}
