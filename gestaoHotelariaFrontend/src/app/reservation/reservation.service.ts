import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/api/reservation/';

  constructor(private http: HttpClient) { }

  getAllReservations(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}getReservationList`);
  }

  updateCheckinReservation(id: number, actualCheckinDate: Date): Observable<any> {
    const body = {
      id: id,
      actualCheckinDate: actualCheckinDate.toISOString()
    };
    return this.http.post<any>(`${this.apiUrl}checkinReservation`, body);
  }

  updateCheckoutReservation(id: number, actualCheckoutDate: Date): Observable<any> {
    const body = {
      id: id,
      actualCheckoutDate: actualCheckoutDate.toISOString()
    };
    return this.http.post<any>(`${this.apiUrl}checkoutReservation`, body);
  }

}
