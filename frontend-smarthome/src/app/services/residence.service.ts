import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Residence } from '../models/residence.model';

@Injectable({
  providedIn: 'root'
})
export class ResidenceService {
  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  createResidence(residence: Residence): Observable<Residence> {
    return this.http.post<Residence>(`${this.apiUrl}/residences`, residence, {
      headers: this.getHeaders()
    });
  }

  getResidenceByUser(userId: number): Observable<Residence> {
    return this.http.get<Residence>(`${this.apiUrl}/residences/user/${userId}`, {
      headers: this.getHeaders()
    });
  }

  checkResidenceExists(userId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/residences/check/${userId}`, {
      headers: this.getHeaders()
    });
  }

  getAllResidences(): Observable<Residence[]> {
    return this.http.get<Residence[]>(`${this.apiUrl}/residences`, {
      headers: this.getHeaders()
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
