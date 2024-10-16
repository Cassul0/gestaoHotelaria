package com.gabriel.desafio.dto;

import java.time.LocalDateTime;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;

public class ReservationDTO {

	private Long id;
	private Guest guest;
	private LocalDateTime expectedCheckinDate;
	private LocalDateTime expectedCheckoutDate;
	private LocalDateTime actualCheckinDate;
	private LocalDateTime actualCheckoutDate;
	private Double dailyValue;
	private Integer days;
	private Boolean parkingSlot;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}
	
	public void setGuest(Guest guest) {
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
	
	public Double getDailyValue() {
		return dailyValue;
	}
	
	public void setDailyValue(Double dailyValue) {
		this.dailyValue = dailyValue;
	}
	
	public Integer getDays() {
		return days;
	}
	
	public void setDays(Integer days) {
		this.days = days;
	}

	public Boolean getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(Boolean parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	
	public Reservation build() {
		return new Reservation(this.guest,
							   this.expectedCheckinDate,
							   this.expectedCheckoutDate,
							   this.actualCheckinDate,
							   this.actualCheckoutDate,
							   this.dailyValue,
							   this.days,
							   this.parkingSlot);	
	}
	
}
