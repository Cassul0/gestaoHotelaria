import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GuestService } from '../guest/guest.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-guest-registration',
  templateUrl: './guest-registration.component.html',
  styleUrls: ['./guest-registration.component.css']
})
export class GuestRegistrationComponent {
  guest = {
    name: '',
    document: '',
    phone: ''
  };

  constructor(private guestService: GuestService, private snackBar: MatSnackBar) {}

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.guestService.registerGuest(this.guest).subscribe(response => {
        this.showSnackbar('Hóspede cadastrado com sucesso!');
        form.resetForm();
      }, error => {
        console.error('Erro ao cadastrar hóspede', error);
        this.showSnackbar('Erro ao cadastrar hóspede');
      });
    }
  }

  showSnackbar(message: string): void {
    this.snackBar.open(message, 'OK', {
      duration: 3000
    });
  }
}
