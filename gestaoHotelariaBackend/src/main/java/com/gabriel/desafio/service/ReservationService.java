package com.gabriel.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired private ReservationRepository reservationRepository;
	
	public void saveReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
}
