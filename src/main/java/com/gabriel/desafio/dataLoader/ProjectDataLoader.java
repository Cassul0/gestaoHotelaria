package com.gabriel.desafio.dataLoader;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;
import com.gabriel.desafio.repository.GuestRepository;
import com.gabriel.desafio.repository.ReservationRepository;

@Component
public class ProjectDataLoader implements CommandLineRunner {

	@Autowired GuestRepository guestRepository;
	@Autowired ReservationRepository reservationRepository;
	
	@Override
	public void run(String... args) throws Exception {
        Guest guest1 = new Guest("Joao", "123456789", "9999999999");
        Guest guest2 = new Guest("Maria", "987654321", "6666666666");
        Guest guest3 = new Guest("Fernando", "123789654", "3333333333");

        guestRepository.save(guest1);
        guestRepository.save(guest2);
        guestRepository.save(guest3);
        System.out.println("Initial guests added to the database.");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        Reservation reservation1 = new Reservation(guest1, dateFormat.parse("16/10/2024 14:00"), dateFormat.parse("18/10/2024 12:00"), dateFormat.parse("16/10/2024 14:05"), dateFormat.parse("18/10/2024 11:30"), 240.0, Integer.valueOf(2), Boolean.TRUE);
        Reservation reservation2 = new Reservation(guest2, dateFormat.parse("20/10/2024 14:00"), dateFormat.parse("22/10/2024 12:00"), dateFormat.parse("20/10/2024 14:01"), null, 300.0, Integer.valueOf(2), Boolean.TRUE);
        Reservation reservation3 = new Reservation(guest3, dateFormat.parse("23/10/2024 14:00"), dateFormat.parse("27/10/2024 12:00"), null, null, 600.0, Integer.valueOf(4), Boolean.FALSE);

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
        System.out.println("Initial reservations added to the database.");
            
	}

}
