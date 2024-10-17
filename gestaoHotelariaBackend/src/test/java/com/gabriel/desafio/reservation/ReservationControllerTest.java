package com.gabriel.desafio.reservation;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.desafio.controller.ReservationController;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.service.PaymentService;
import com.gabriel.desafio.service.ReservationService;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
	
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@MockBean private PaymentService paymentService;
	@MockBean private ReservationService reservationService;
	
	
	@Test
	public void testSaveReservation() throws Exception {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("16/10/2024 14:05", dateTimeFormatter), LocalDateTime.parse("18/10/2024 11:30", dateTimeFormatter), Integer.valueOf(2), Boolean.TRUE);
      
        when(reservationService.saveReservation(any(Reservation.class))).thenReturn(reservation);
        
        mockMvc.perform(post("/api/reservation/create")
        	   .contentType("application/json")
        	   .content(objectMapper.writeValueAsString(reservation)))
        	   .andExpect(status().isCreated())
        	   .andExpect(content().string("Reserva criada com sucesso!"));
	}
	
	@Test
	public void testGetAllReservation() throws Exception {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        Guest guest2 = new Guest(2L, "Leonardo", "45654654", "789546123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("16/10/2024 14:05", dateTimeFormatter), LocalDateTime.parse("18/10/2024 11:30", dateTimeFormatter), Integer.valueOf(2), Boolean.TRUE);
        Reservation reservation2 = new Reservation(2L, guest2, LocalDateTime.parse("20/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("22/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("20/10/2024 14:01", dateTimeFormatter), null, Integer.valueOf(2), Boolean.TRUE);

        when(reservationService.getReservationList()).thenReturn(List.of(reservation, reservation2));

        mockMvc.perform(get("/api/guest/getReservationList")) 
 		 	   .andExpect(status().isOk())
 		 	   .andExpect(status().isOk())
 		 	   .andExpect(jsonPath("$[0].id").value(1L))
 		 	   .andExpect(jsonPath("$[1].id").value(2L));
	}
	
	@Test
	public void testInvalidCheckin() throws Exception {
		Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), null, null, Integer.valueOf(2), Boolean.TRUE);

        when(reservationService.checkinReservation(any(Reservation.class), LocalDateTime.parse("16/10/2024 11:00", dateTimeFormatter)))
            .thenReturn("A data de checkin atual é anterior a data de checkin esperada, tente novamente mais tarde!");

        mockMvc.perform(post("/api/reservation/checkinReservation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(content().string("A data de checkin atual é anterior a data de checkin esperada, tente novamente mais tarde!"));
	}
	

	
}