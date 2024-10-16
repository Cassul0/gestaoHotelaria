package com.gabriel.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.repository.GuestRepository;

@Service
public class GuestService {
	
    @Autowired private GuestRepository guestRepository;

    public void saveGuest(Guest guest) {
    	guestRepository.save(guest);
    }
    
	public Optional<Guest> getGuestById(Long id) {
		return guestRepository.findById(id);
	}
	
	public Optional<Guest> getGuestByName(String name){
		return guestRepository.findGuestByName(name);
	}

	public Optional<Guest> getGuestByDocument(String document) {
		return guestRepository.findGuestByDocument(document);
	}

	public Optional<Guest> getGuestByPhone(String phone) {
		return guestRepository.findGuestByPhone(phone);
	}

	public List<Guest> getGuestsStillAtTheHotel() {
		return guestRepository.getGuestsStillAtTheHotel();
	}

	public List<Guest> getGuestsThatHaventCheckin() {
		return guestRepository.getGuestsThatHaventCheckin();
	}
}
