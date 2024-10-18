import { GuestService } from './../../guest/guest.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ReservationService } from '../reservation.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent implements OnInit {
  guests: any[] = [];
  reservation = {
    guest: '',
    expectedCheckinDate: '',
    expectedCheckoutDate: '',
    parkingSlot: ''
  };

  constructor(private reservationService: ReservationService, private guestService: GuestService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadGuests();
  }

  loadGuests(): void {
    this.guestService.getAllGuests().subscribe(response => {
      this.guests = response;
    });
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.reservationService.createReservation(this.reservation).subscribe(response => {
        this.showSnackbar('Reserva cadastrada com sucesso!');
        form.resetForm();
      }, error => {
        console.error('Erro ao cadastrar reserva', error);
        this.showSnackbar('Erro ao cadastrar reserva');
      });
    }
  }

  showSnackbar(message: string): void {
    this.snackBar.open(message, 'OK', {
      duration: 3000
    });
  }
}
