package com.gabriel.desafio.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.repository.GuestRepository;

@Component
public class ProjectDataLoader implements CommandLineRunner {

	@Autowired GuestRepository guestRepository;
	
	@Override
	public void run(String... args) throws Exception {
		if (guestRepository.count() == 0) {
            Guest guest1 = new Guest("Joao", "123456789", "9999999999");
            Guest guest2 = new Guest("Maria", "987654321", "6666666666");
            Guest guest3 = new Guest("Fernando", "123789654", "3333333333");

            guestRepository.save(guest1);
            guestRepository.save(guest2);
            guestRepository.save(guest3);
            System.out.println("Initial guests added to the database.");
        } else {
            System.out.println("Guests already exist in the database.");
        }		
	}

}
