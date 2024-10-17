import { Component } from '@angular/core';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  displayedColumns: string[] = ['id',
                                'expectedCheckinDate',
                                'expectedCheckoutDate',
                                'actualCheckinDate',
                                'actualCheckoutDate',
                                'days',
                                'parkingSlot'];
  data: any;

  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.reservationService.getAllReservations().subscribe(response => {
      this.data = response;
    })
  }

  getAllReservations(): void {
    this.reservationService.getAllReservations().subscribe(response => {
      this.data = response;
    })
  }

}
