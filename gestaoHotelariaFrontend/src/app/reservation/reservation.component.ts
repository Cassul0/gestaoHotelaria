import { Component } from '@angular/core';
import { ReservationService } from './reservation.service';
import { MatDialog } from '@angular/material/dialog';
import { DateModalComponent } from '../date-modal/date-modal.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PaymentModalComponent } from '../payment/payment-modal/payment-modal.component';  // Novo caminho

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  displayedColumns: string[] = ['id',
                                'expectedCheckinDate',
                                'actualCheckinDate',
                                'expectedCheckoutDate',
                                'actualCheckoutDate',
                                'days',
                                'parkingSlot',
                                'actions'];
  data: any;

  constructor(private reservationService: ReservationService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

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

  onCheckinClick(reservationId: number): void {
    const dialogRef = this.dialog.open(DateModalComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.realizarCheckin(reservationId, result);
      }
    });
  }

  onCheckoutClick(reservationId: number): void {
    const dialogRef = this.dialog.open(DateModalComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.realizarCheckout(reservationId, result);
      }
    });
  }

  realizarCheckin(reservationId: number, data: Date): void {
    this.reservationService.updateCheckinReservation(reservationId, data).subscribe(response => {
      this.showSnackbar(response.message || response);
    }, error => {
      this.showSnackbar(error.error.message || 'Erro ao realizar check-in');
    });
  }

  realizarCheckout(reservationId: number, data: Date): void {
    this.reservationService.updateCheckoutReservation(reservationId, data).subscribe(response => {
      if (response.payment) {
        this.dialog.open(PaymentModalComponent, {
          data: response.payment
        });
      }
      this.showSnackbar(response.message);
    }, error => {
      console.error('Erro ao realizar check-out', error);
      this.showSnackbar(error.error.message || 'Erro ao realizar check-out');
    });
  }

  showSnackbar(message: string): void {
    this.snackBar.open(message, 'OK', {
      duration: 3000
    }).afterDismissed().subscribe(() => {
      this.getAllReservations();
    });
  }
}
