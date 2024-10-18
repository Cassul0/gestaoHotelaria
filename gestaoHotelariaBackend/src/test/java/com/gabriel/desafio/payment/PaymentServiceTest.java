package com.gabriel.desafio.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gabriel.desafio.dataLoader.ProjectDataLoader;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.PaymentRepository;
import com.gabriel.desafio.service.PaymentService;

@SpringBootTest
public class PaymentServiceTest {

	@Autowired private PaymentService paymentService;
	
	@MockBean private PaymentRepository paymentRepository;
	
	//Não executa nos testes a classe utilizada para gerar os registros.
	@MockBean private ProjectDataLoader projectDataLoader;
	
	@Test
	public void testCheckoutPayment() {
		Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("20/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("22/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("20/10/2024 14:05", dateTimeFormatter),  null, Integer.valueOf(2), Boolean.TRUE);
        //expectedCheckoutDate = 22/10/2024 12:00
        reservation.setActualCheckoutDate(LocalDateTime.parse("22/10/2024 12:00", dateTimeFormatter));
        Payment createdPayment = paymentService.generateCheckoutPayment(reservation);
        
        assertThat(createdPayment).isNotNull();
        assertThat(createdPayment.getDailyValue()).isEqualTo(300.00);
        assertThat(createdPayment.getParkingFee()).isEqualTo(35.00);
        assertThat(createdPayment.getTotal()).isEqualTo(335.00);
	}
	
	@Test
	public void testLateCheckoutPayment() {
		Guest guest = new Guest(1L, "José", "123456789", "123123123");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Reservation reservation = new Reservation(1L, guest, LocalDateTime.parse("20/10/2024 14:00", dateTimeFormatter), LocalDateTime.parse("22/10/2024 12:00", dateTimeFormatter), LocalDateTime.parse("20/10/2024 14:05", dateTimeFormatter),  null, Integer.valueOf(2), Boolean.TRUE);
        //expectedCheckoutDate = 22/10/2024 12:00
        reservation.setActualCheckoutDate(LocalDateTime.parse("22/10/2024 13:00", dateTimeFormatter));
        Payment createdPayment = paymentService.generateCheckoutPayment(reservation);
        
        assertThat(createdPayment).isNotNull();
        assertThat(createdPayment.getDailyValue()).isEqualTo(300.00);
        assertThat(createdPayment.getParkingFee()).isEqualTo(35.00);
        assertThat(createdPayment.getLateCheckoutFee()).isEqualTo(60.00);
        assertThat(createdPayment.getTotal()).isEqualTo(395.00);
	}
	
}
