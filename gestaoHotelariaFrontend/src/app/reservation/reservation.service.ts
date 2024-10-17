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

  updateCheckinReservation(reservationId: number, data: Date): Observable<any>{
    const body = {
      id: reservationId,
      actualCheckinDate: data.toISOString()
    };
    return this.http.post<any>(`${this.apiUrl}checkinReservation`, body);
  }

}
