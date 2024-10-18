import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuestComponent } from './guest/guest.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { ReservationComponent } from './reservation/reservation.component';
import { GuestRegistrationComponent } from './guest-registration/guest-registration.component';
import { PaymentListComponent } from './payment/payment-list/payment-list.component';

const routes: Routes = [
  { path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'guests', component: GuestComponent },
      { path: 'reservation', component: ReservationComponent },
      { path: 'guest-registration', component: GuestRegistrationComponent},
      { path: 'payment-list', component: PaymentListComponent }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
