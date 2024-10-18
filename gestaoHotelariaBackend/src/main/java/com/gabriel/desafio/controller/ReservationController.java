package com.gabriel.desafio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.desafio.dto.PaymentDTO;
import com.gabriel.desafio.dto.ReservationDTO;
import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.restException.ReservationRestException;
import com.gabriel.desafio.service.PaymentService;
import com.gabriel.desafio.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired ReservationService reservationService;
	@Autowired private PaymentService paymentService;

	
	@PostMapping("/create")
	public ResponseEntity<Map<String, String>>saveReservation(@RequestBody ReservationDTO reservationDTO){
		Map<String, String> response = new HashMap<>();
		try {
			reservationService.saveReservation(reservationDTO.build());
			response.put("message", "Reserva criada com sucesso!");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
		} catch(Exception e) {
			response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
	
	@PostMapping("/checkinReservation")
	public ResponseEntity<Map<String, String>> updateReservationCheckin(@RequestBody ReservationDTO reservationDTO) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        Optional<Reservation> reservation = reservationService.getReservationById(reservationDTO.getId());
	        if (reservation.isPresent()) {
	            String message = reservationService.checkinReservation(reservation.get(), reservationDTO.getActualCheckinDate());
	            response.put("message", message);
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        }
	    } catch (ReservationRestException e) {
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	    response.put("message", "Ocorreu um erro inesperado!");
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	
	@PostMapping("/checkoutReservation")
	public ResponseEntity<Map<String, Object>> updateReservationCheckout(@RequestBody ReservationDTO reservationDTO){
	    Map<String, Object> response = new HashMap<>();
	    try {
			Optional<Reservation> reservation = reservationService.getReservationById(reservationDTO.getId());
			if(reservation.isPresent()) {
				String message = reservationService.checkoutReservation(reservation.get(), reservationDTO.getActualCheckoutDate());
				reservation.get().setActualCheckoutDate(reservationDTO.getActualCheckoutDate());
				Payment payment = paymentService.generateCheckoutPayment(reservation.get());
				response.put("message", message);
				response.put("payment", new PaymentDTO(payment));
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				response.put("message", "Ocorreu um erro ao realizar o checkout");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
	    }catch(Exception e) {
	    	response.put("message", "Ocorreu um erro inesperado!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	@GetMapping("/getReservationList")
	public ResponseEntity<List<ReservationDTO>> getReservationList(){
		List<ReservationDTO> reservationDTOList = ReservationDTO.build(reservationService.getReservationList());
		return ResponseEntity.ok(reservationDTOList);
	}
}
