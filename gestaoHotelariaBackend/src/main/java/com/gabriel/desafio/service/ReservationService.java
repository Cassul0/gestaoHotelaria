package com.gabriel.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired private ReservationRepository reservationRepository;
	
	public void saveReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
	public Optional<Reservation> getReservationById(Long id) {
		return reservationRepository.findById(id);
	}
	
	public List<Reservation> getReservationList(){
		return reservationRepository.findAll();
	}
	
	public String checkinReservation(Reservation reservation, LocalDateTime checkinDate) {
		if(reservation.getExpectedCheckinDate().isAfter(checkinDate)) {
			return "A data de checkin atual é anterior a data de checkin esperada, tente novamente mais tarde!";
		}
		reservation.setActualCheckinDate(checkinDate);
		this.saveReservation(reservation);
		return "Checkin executado com sucesso";
	}
	
	public void checkoutReservation(Reservation reservation, LocalDateTime checkoutDate) {
		reservation.setActualCheckoutDate(checkoutDate);
		this.saveReservation(reservation);
	}
	
	
}
