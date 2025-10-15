import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    console.log('Enviando dados para o backend:', user);
    return this.http.post<User>(`${this.apiUrl}/users`, user);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users`, { headers: this.getHeaders() });
  }

  getUserByEmail(email: string): Observable<User | undefined> {
    return new Observable<User | undefined>((subscriber) => {
      this.getAllUsers().subscribe({
        next: (users) => {
          const found = users.find(u => u.email === email);
          subscriber.next(found);
          subscriber.complete();
        },
        error: (err) => subscriber.error(err)
      });
    });
  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
}
