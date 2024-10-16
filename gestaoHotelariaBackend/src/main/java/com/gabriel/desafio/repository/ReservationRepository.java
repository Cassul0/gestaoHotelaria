package com.gabriel.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.desafio.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
