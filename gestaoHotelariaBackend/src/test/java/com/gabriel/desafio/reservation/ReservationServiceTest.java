package com.gabriel.desafio.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gabriel.desafio.dataLoader.ProjectDataLoader;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.ReservationRepository;
import com.gabriel.desafio.restException.ReservationRestException;
import com.gabriel.desafio.service.ReservationService;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired private ReservationService reservationService;
	
	@MockBean private ReservationRepository reservationRepository;
	
	//Não executa nos testes a classe utilizada para gerar os registros.
	@MockBean private ProjectDataLoader projectDataLoader;
	
	@Test
	public void testSaveReservation() {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("16/10/2024 14:05", dateTimeFormatter), LocalDateTime.parse("18/10/2024 11:30", dateTimeFormatter), Integer.valueOf(2), Boolean.TRUE);
        
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        
        Reservation createdReservation = reservationService.saveReservation(reservation);
        
        assertThat(createdReservation).isNotNull();
        assertThat(createdReservation.getGuest()).isNotNull();
        assertThat(createdReservation.getId()).isEqualTo(1L);
        assertThat(createdReservation.getExpectedCheckinDate()).isEqualTo(LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter));
	    assertThat(createdReservation.getActualCheckinDate()).isEqualTo(LocalDateTime.parse("16/10/2024 14:05", dateTimeFormatter));
	    assertThat(createdReservation.getExpectedCheckoutDate()).isEqualTo(LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter));
	    assertThat(createdReservation.getActualCheckoutDate()).isEqualTo(LocalDateTime.parse("18/10/2024 11:30", dateTimeFormatter));
        assertThat(createdReservation.getDays()).isEqualTo(2);
        assertThat(createdReservation.getParkingSlot()).isEqualTo(Boolean.TRUE);
	}
	
	@Test
	public void testGetAllReservation() {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        Guest guest2 = new Guest(2L, "Leonardo", "45654654", "789546123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("16/10/2024 14:05", dateTimeFormatter), LocalDateTime.parse("18/10/2024 11:30", dateTimeFormatter), Integer.valueOf(2), Boolean.TRUE);
        Reservation reservation2 = new Reservation(2L, guest2, LocalDateTime.parse("20/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("22/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("20/10/2024 14:01", dateTimeFormatter), null, Integer.valueOf(2), Boolean.TRUE);

        when(reservationRepository.findAll()).thenReturn(List.of(reservation, reservation2));
        
        List<Reservation> result = reservationService.getReservationList();
        
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);
	}
	
	@Test
	public void testInvalidCheckin() {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), null, null, Integer.valueOf(2), Boolean.TRUE);

        String result;
		try {
			result = reservationService.checkinReservation(reservation, LocalDateTime.parse("16/10/2024 11:00", dateTimeFormatter));
	        assertThat(result).isEqualTo("A data de checkin atual é anterior a data de checkin esperada, tente novamente mais tarde!");
		} catch (ReservationRestException e) {
			e.printStackTrace();
		}
        
	}
	
	@Test
	public void testValidCheckin()  {
		   Guest guest = new Guest(1L, "José", "123456789", "123123123");
	        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("16/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("18/10/2024 12:00", dateTimeFormatter), null, null, Integer.valueOf(2), Boolean.TRUE);

	        String result;
			try {
				result = reservationService.checkinReservation(reservation, LocalDateTime.parse("16/10/2024 15:00", dateTimeFormatter));
		        assertThat(result).isEqualTo("Checkin executado com sucesso");
			} catch (ReservationRestException e) {
				e.printStackTrace();
			}
	        
	}
	
}
