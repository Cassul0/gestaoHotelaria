package com.gabriel.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.ReservationRepository;
import com.gabriel.desafio.restException.ReservationRestException;

@Service
public class ReservationService {

	@Autowired private ReservationRepository reservationRepository;
	
	public Reservation saveReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	public Optional<Reservation> getReservationById(Long id) {
		return reservationRepository.findById(id);
	}
	
	public List<Reservation> getReservationList(){
		return reservationRepository.findAll();
	}
	
	public String checkinReservation(Reservation reservation, LocalDateTime checkinDate) throws ReservationRestException {
		if(reservation.getExpectedCheckinDate().isAfter(checkinDate)) {
			throw new ReservationRestException("A data de checkin atual é anterior a data de checkin esperada, tente novamente mais tarde!");
		}
		reservation.setActualCheckinDate(checkinDate);
		this.saveReservation(reservation);
		return "Checkin executado com sucesso";
	}
	
	public String checkoutReservation(Reservation reservation, LocalDateTime checkoutDate) {
		reservation.setActualCheckoutDate(checkoutDate);
		this.saveReservation(reservation);
		return "Checkin executado com sucesso";
	}
	
	
}
