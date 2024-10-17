import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  private apiUrl = 'http://localhost:8080/api/guest/';

  constructor(private http: HttpClient) { }

  getAllGuests(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}findAllGuests`);
  }

  getGuestsStillAtTheHotel(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}findGuestsStillAtTheHotel`);
  }

}
