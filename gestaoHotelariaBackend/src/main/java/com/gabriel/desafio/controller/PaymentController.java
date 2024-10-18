package com.gabriel.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired PaymentService paymentService;
	
	@GetMapping("/findAllPayments")
    public ResponseEntity<List<Payment>> getAllPayments(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
}
