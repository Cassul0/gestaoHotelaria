import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-date-modal',
  templateUrl: './date-modal.component.html',
})
export class DateModalComponent {
  data: string | null = null;

  constructor(public dialogRef: MatDialogRef<DateModalComponent>) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onConfirm(): void {
    const date = this.data ? new Date(this.data) : null;
    this.dialogRef.close(date);
  }
}
