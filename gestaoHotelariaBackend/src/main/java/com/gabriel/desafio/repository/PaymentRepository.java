package com.gabriel.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.desafio.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
