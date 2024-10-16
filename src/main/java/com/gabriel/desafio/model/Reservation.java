package com.gabriel.desafio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Guest guest;
	
	private LocalDateTime expectedCheckinDate;
	private LocalDateTime expectedCheckoutDate;
	private LocalDateTime actualCheckinDate;
	private LocalDateTime actualCheckoutDate;
	private Double dailyValue;
	private String status; 
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
