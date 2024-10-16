package com.gabriel.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabriel.desafio.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{
	
	@Query(" select g from Guest g where g.name like :name ")
	Optional<Guest> findGuestByName(String name);

	@Query(" select g from Guest g where g.document like :document")
	Optional<Guest> findGuestByDocument(String document);

	@Query(" select g from Guest g where g.phone like :phone")
	Optional<Guest> findGuestByPhone(String phone);
	
}
