package com.gabriel.desafio.guest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.gabriel.desafio.controller.GuestController;
import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.service.GuestService;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

	@Autowired private MockMvc mockMvc;
	
    @MockBean private GuestService guestService;

    @Autowired private ObjectMapper objectMapper;
    
    @Test
    public void testSaveGuest() throws Exception {
        Guest guest = new Guest(1L, "José", "123456789", "123123123");

        when(guestService.saveGuest(any(Guest.class))).thenReturn(guest);

        mockMvc.perform(post("/api/guest/create")
               .contentType("application/json")
               .content(objectMapper.writeValueAsString(guest)))
               .andExpect(status().isCreated())
               .andExpect(content().string("Hospede criado com sucesso!"));
    }
    
	
	  @Test 
	  public void testFindAllGuests() throws Exception { 
		  Guest guest = new Guest(1L, "José", "123456789", "123123123"); 
		  Guest guest2 = new Guest(2L, "Leonardo", "45654654", "789546123");
		  
		  when(guestService.getAllGuests()).thenReturn(List.of(guest, guest2));
	  
		  mockMvc.perform(get("/api/guest/findAllGuests")) 
		  		 .andExpect(status().isOk())
		  		 .andExpect(jsonPath("$[0].name").value("José"))
		  		 .andExpect(jsonPath("$[0].document").value("123456789"))
		  		 .andExpect(jsonPath("$[0].phone").value("123123123"))
		  		 .andExpect(jsonPath("$[1].name").value("Leonardo"))
		  		 .andExpect(jsonPath("$[1].document").value("45654654"))
		  		 .andExpect(jsonPath("$[1].phone").value("789546123")); 
	  }
		  
	  @Test 
	  public void testGetGuestById() throws Exception { 
		  Guest guest = new Guest(1L, "Leonardo", "123456789", "123123123");
		  
		  when(guestService.getGuestById(1L)).thenReturn(Optional.of(guest));
		  
		  mockMvc.perform(get("/api/guest/findGuestById/1") 
				 .contentType("application/json"))
		  		 .andExpect(status().isOk()) 
		  		 .andExpect(jsonPath("$.id").value(1L))
	  		 	 .andExpect(jsonPath("$.name").value("Leonardo")); 
	  }
	  
	  @Test 
	  public void testGetGuestByName() throws Exception { 
		  Guest guest = new Guest(1L, "Leonardo", "123456789", "123123123");
	  
		  when(guestService.getGuestByName("Leonardo")).thenReturn(Optional.of(guest));
	  
		  mockMvc.perform(get("/api/guest/findGuestByName/Leonardo") 
				 .contentType("application/json"))
		  		 .andExpect(status().isOk()) 
		  		 .andExpect(jsonPath("$.name").value("Leonardo")); 
	  }
	 
}
