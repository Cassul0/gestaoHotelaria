import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentModalComponent } from './payment-modal/payment-modal.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { PaymentListComponent } from './payment-list/payment-list.component';
import { MatTableModule } from '@angular/material/table';


@NgModule({
  declarations: [
    PaymentModalComponent,
    PaymentListComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    MatTableModule,
    MatButtonModule
  ],
  exports: [
    PaymentModalComponent
  ]
})
export class PaymentModule { }
