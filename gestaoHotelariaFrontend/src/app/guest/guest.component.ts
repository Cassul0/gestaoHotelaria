import { GuestService } from './guest.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.css']
})
export class GuestComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'document', 'phone'];
  data: any;

  constructor(private guestService: GuestService) { }

  ngOnInit(): void {
    this.guestService.getAllGuests().subscribe(response => {
      this.data = response;
    });
  }

  getAllGuests(): void {
    this.guestService.getAllGuests().subscribe(response => {
      this.data = response;
    })
  }

  getGuestsStillAtTheHotel(): void {
    this.guestService.getGuestsStillAtTheHotel().subscribe(response => {
      this.data = response;
    })
  }

}
