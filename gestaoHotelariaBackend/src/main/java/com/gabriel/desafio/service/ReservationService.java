package com.gabriel.desafio.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired private ReservationRepository reservationRepository;
	@Autowired private PaymentService paymentService;
	
	public void saveReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
	public Optional<Reservation> getReservationById(Long id) {
		return reservationRepository.findById(id);
	}
	
	public List<Reservation> getReservationList(){
		return reservationRepository.findAll();
	}
	
	public String checkinReservation(Reservation reservation, Date checkinDate) {
		if(reservation.getExpectedCheckinDate().after(checkinDate)) {
			return "A data de checkin atual é anterior a data de checkin esperada";
		}
		reservation.setActualCheckinDate(checkinDate);
		this.saveReservation(reservation);
		return "Checkin executado com sucesso";
	}
	
	public String checkoutReservation(Reservation reservation, Date checkoutDate) {
		reservation.setActualCheckinDate(checkoutDate);
		this.saveReservation(reservation);
		paymentService.generateCheckoutPayment(reservation);
		return "Checkout executado com sucesso";
	}
	
	
}
