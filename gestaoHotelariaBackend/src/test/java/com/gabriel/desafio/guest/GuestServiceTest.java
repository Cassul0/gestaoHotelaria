package com.gabriel.desafio.guest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gabriel.desafio.dataLoader.ProjectDataLoader;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.repository.GuestRepository;
import com.gabriel.desafio.service.GuestService;

@SpringBootTest
public class GuestServiceTest {

	@Autowired private GuestService guestService;
	
	@MockBean private GuestRepository guestRepository;
	
	//Não executa nos testes a classe utilizada para gerar os registros.
	@MockBean private ProjectDataLoader projectDataLoader;
	
	@Test
	public void testSaveGuest() {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
       
        when(guestRepository.save(any(Guest.class))).thenReturn(guest);
        
        Guest createdGuest = guestService.saveGuest(guest);

        assertThat(createdGuest).isNotNull();
        assertThat(createdGuest.getId()).isEqualTo(1L);
        assertThat(createdGuest.getName()).isEqualTo("José");
        assertThat(createdGuest.getDocument()).isEqualTo("123456789");
        assertThat(createdGuest.getPhone()).isEqualTo("123123123");
	}
	
	@Test
	public void testGetGuestById() {
	    Guest guest = new Guest(1L, "José", "123456789", "123123123");

	    when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

	    Optional<Guest> foundGuest = guestService.getGuestById(1L);

	    assertThat(foundGuest).isPresent();
	    assertThat(foundGuest.get().getId()).isEqualTo(1L);
	}
	
	@Test
    public void testGetAllGuests() {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");
        Guest guest2 = new Guest(2L, "Leonardo", "45654654", "789546123");
        
        when(guestRepository.findAll()).thenReturn(List.of(guest, guest2));

        List<Guest> result = guestService.getAllGuests();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("José");
        assertThat(result.get(1).getName()).isEqualTo("Leonardo");
    }
	
	@Test
	public void testGetGuestByName() {
	    Guest guest = new Guest(1L, "José", "123456789", "123123123");

	    when(guestRepository.findGuestByName("José")).thenReturn(Optional.of(guest));

	    Optional<Guest> foundGuest = guestService.getGuestByName("José");

	    assertThat(foundGuest).isPresent();
	    assertThat(foundGuest.get().getName()).isEqualTo("José");
	}
 }
