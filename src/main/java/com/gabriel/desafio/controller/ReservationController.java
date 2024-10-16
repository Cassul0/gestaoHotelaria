package com.gabriel.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.desafio.dto.ReservationDTO;
import com.gabriel.desafio.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired ReservationService reservationService;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveReservation(@RequestBody ReservationDTO reservationDTO){
		try {
			reservationService.saveReservation(reservationDTO.build());
	        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created successfully!"); 
		} catch(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating reservation: " + e.getMessage()); 
		}
	}
	
	@PostMapping("/checkinReservation")
	public ResponseEntity<String> updateReservationCheckin(){
		return null;
		
	}
}
