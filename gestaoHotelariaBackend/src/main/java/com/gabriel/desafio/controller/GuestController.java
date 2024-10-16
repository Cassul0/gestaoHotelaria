package com.gabriel.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.desafio.dto.GuestDTO;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.service.GuestService;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

	@Autowired GuestService guestService;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveGuest(@RequestBody GuestDTO guest){
		try {
			guestService.saveGuest(guest.build());
	        return ResponseEntity.status(HttpStatus.CREATED).body("Guest created successfully!"); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating guest: " + e.getMessage()); 
	    }
	}
	
    @GetMapping("/findGuestById/{id}")
    public ResponseEntity<Optional<Guest>> getGuestById(@PathVariable Long id) {
        Optional<Guest> guest = guestService.getGuestById(id);
        return guest.isPresent() ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }	
	
    @GetMapping("/findGuestByName/{name}")
    public ResponseEntity<Optional<Guest>> getGuestByName(@PathVariable String name) {
        Optional<Guest> guest = guestService.getGuestByName(name);
        return guest.isPresent() ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }	
    
    
    @GetMapping("/findGuestByDocument/{document}")
    public ResponseEntity<Optional<Guest>> getGuestByDocument(@PathVariable String document) {
        Optional<Guest> guest = guestService.getGuestByDocument(document);
        return guest.isPresent() ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }	
    
    @GetMapping("/findGuestByPhone/{phone}")
    public ResponseEntity<Optional<Guest>> getGuestByPhone(@PathVariable String phone) {
        Optional<Guest> guest = guestService.getGuestByPhone(phone);
        return guest.isPresent() ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }	
    
    @GetMapping("/findGuestsStillAtTheHotel")
    public ResponseEntity<List<Guest>> getGuestsStillAtTheHotel(){
		return ResponseEntity.ok(guestService.getGuestsStillAtTheHotel());
    }
    
    @GetMapping("/findGuestsThatHaventCheckedIn")
    public ResponseEntity<List<Guest>> getGuestsThatHaventCheckedIn(){
		return ResponseEntity.ok(guestService.getGuestsThatHaventCheckedIn());
    }
	
}
