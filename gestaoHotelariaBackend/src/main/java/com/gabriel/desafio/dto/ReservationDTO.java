package com.gabriel.desafio.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.gabriel.desafio.model.Guest;
import com.gabriel.desafio.model.Reservation;

public class ReservationDTO {

	private Long id;
	private Guest guest;
	private Date expectedCheckinDate;
	private Date expectedCheckoutDate;
	private Date actualCheckinDate;
	private Date actualCheckoutDate;
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
	
	public Date getExpectedCheckinDate() {
		return expectedCheckinDate;
	}
	
	public void setExpectedCheckinDate(Date expectedCheckinDate) {
		this.expectedCheckinDate = expectedCheckinDate;
	}
	
	public Date getExpectedCheckoutDate() {
		return expectedCheckoutDate;
	}
	
	public void setExpectedCheckoutDate(Date expectedCheckoutDate) {
		this.expectedCheckoutDate = expectedCheckoutDate;
	}
	public Date getActualCheckinDate() {
		return actualCheckinDate;
	}
	
	public void setActualCheckinDate(Date actualCheckinDate) {
		this.actualCheckinDate = actualCheckinDate;
	}
	
	public Date getActualCheckoutDate() {
		return actualCheckoutDate;
	}
	
	public void setActualCheckoutDate(Date actualCheckoutDate) {
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
