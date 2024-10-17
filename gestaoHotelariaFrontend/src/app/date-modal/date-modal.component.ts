import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-date-modal',
  templateUrl: './date-modal.component.html',
  styleUrl: './date-modal.component.css'
})
export class DateModalComponent {
  data: Date | null = null;

  constructor(public dialogRef: MatDialogRef<DateModalComponent>) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onConfirm(): void {
    this.dialogRef.close(this.data);
  }

}
