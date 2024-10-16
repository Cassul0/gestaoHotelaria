package com.gabriel.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired PaymentRepository paymentRepository;
	
	public void savePayment(Payment payment) {
		paymentRepository.save(payment);
	}
	
	public Payment generateCheckoutPayment(Reservation reservation) {
		Payment payment = new Payment(reservation, reservation.getDayCount(), reservation.isCheckoutLate(), reservation.isActualCheckoutWeekendDay());
		
		this.savePayment(payment);
		
		return payment;
	}
	
}
