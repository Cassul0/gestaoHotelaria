package com.gabriel.desafio.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;

public class ReservationDTO {

	private Long id;
	private GuestDTO guest;
	private LocalDateTime expectedCheckinDate;
	private LocalDateTime expectedCheckoutDate;
	private LocalDateTime actualCheckinDate;
	private LocalDateTime actualCheckoutDate;
	private Integer days;
	private String parkingSlot;
	
	public ReservationDTO(Long id, GuestDTO guest, LocalDateTime expectedCheckinDate, LocalDateTime expectedCheckoutDate,
			LocalDateTime actualCheckinDate, LocalDateTime actualCheckoutDate, Integer days, Boolean parkingSlot) {
		super();
		this.id = id;
		this.guest = guest;
		this.expectedCheckinDate = expectedCheckinDate;
		this.expectedCheckoutDate = expectedCheckoutDate;
		this.actualCheckinDate = actualCheckinDate;
		this.actualCheckoutDate = actualCheckoutDate;
		this.days = days;
		this.parkingSlot = parkingSlot ? "Sim" : "Não" ;
	}
	
	public ReservationDTO() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GuestDTO getGuest() {
		return guest;
	}
	
	public void setGuest(GuestDTO guest) {
		this.guest = guest;
	}
	
	public LocalDateTime getExpectedCheckinDate() {
		return expectedCheckinDate;
	}
	
	public void setExpectedCheckinDate(LocalDateTime expectedCheckinDate) {
		this.expectedCheckinDate = expectedCheckinDate;
	}
	
	public LocalDateTime getExpectedCheckoutDate() {
		return expectedCheckoutDate;
	}
	
	public void setExpectedCheckoutDate(LocalDateTime expectedCheckoutDate) {
		this.expectedCheckoutDate = expectedCheckoutDate;
	}
	public LocalDateTime getActualCheckinDate() {
		return actualCheckinDate;
	}
	
	public void setActualCheckinDate(LocalDateTime actualCheckinDate) {
		this.actualCheckinDate = actualCheckinDate;
	}
	
	public LocalDateTime getActualCheckoutDate() {
		return actualCheckoutDate;
	}
	
	public void setActualCheckoutDate(LocalDateTime actualCheckoutDate) {
		this.actualCheckoutDate = actualCheckoutDate;
	}
	
	public Integer getDays() {
		return days;
	}
	
	public void setDays(Integer days) {
		this.days = days;
	}

	public String getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(String parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	
	public static List<ReservationDTO> build(List<Reservation> reservationList) {
	    return reservationList.stream()
	            .map(reservation -> new ReservationDTO(
	                reservation.getId(),
	                GuestDTO.build(reservation.getGuest()),
	                reservation.getExpectedCheckinDate(),
	                reservation.getExpectedCheckoutDate(),
	                reservation.getActualCheckinDate(),
	                reservation.getActualCheckoutDate(),
	                reservation.getDays(),
	                reservation.getParkingSlot()
	                )
	            )
	            .collect(Collectors.toList());	}

	public Reservation build() {
		return new Reservation(this.getId(),
                GuestDTO.build(this.getGuest()),
                this.getExpectedCheckinDate(),
                this.getExpectedCheckoutDate(),
                this.getActualCheckinDate(),
                this.getActualCheckoutDate(),
                this.getDays(),
                this.getParkingSlot().equals("Sim") ? true : false
                );
	}
	
}
