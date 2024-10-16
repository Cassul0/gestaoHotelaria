package com.gabriel.desafio.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Integer days;
	private Boolean parkingSlot;
	
	public Reservation() { }
	
	public Reservation(Guest guest, LocalDateTime expectedCheckinDate, LocalDateTime expectedCheckoutDate, LocalDateTime actualCheckinDate, 
					   LocalDateTime actualCheckoutDate, Double dailyValue, Integer days, Boolean parkingSlot) {
		this.guest = guest;
		this.expectedCheckinDate = expectedCheckinDate;
		this.expectedCheckoutDate = expectedCheckoutDate;
		this.actualCheckinDate = actualCheckinDate;
		this.actualCheckoutDate = actualCheckoutDate;
		this.dailyValue = dailyValue;
		this.days = days;
		this.parkingSlot = parkingSlot;
	}
	
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
	
	public Long[] getDayCount() {
		LocalDateTime startDate = this.actualCheckinDate;
		LocalDateTime endDate = this.actualCheckoutDate;
		Long weekdayCount = 0L;
		Long weekendDayCount = 0L;
		
		for (LocalDateTime date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
	        DayOfWeek dayOfWeek = date.getDayOfWeek();
			if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
				weekendDayCount++;
			} else {
				weekdayCount++;
			}
		}
		return new Long[] {weekdayCount, weekendDayCount};
	}
	
	public boolean isCheckoutLate() {
		return this.expectedCheckoutDate.isBefore(actualCheckoutDate);
	}
	
	public boolean isActualCheckoutWeekendDay() {
		DayOfWeek dayOfWeek = this.getActualCheckoutDate().getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
	}
}
