import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'reservationId', 'dailyValue', 'parkingFee', 'lateCheckoutFee', 'total'];
  data: any[] = [];

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.getAllPayments();
  }

  getAllPayments(): void {
    this.paymentService.getAllPayments().subscribe(response => {
      this.data = response;
    });
  }
}
