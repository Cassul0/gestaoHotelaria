package com.gabriel.desafio.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"getDayCount", "isCheckoutLate", "isActualCheckoutWeekendDay"})
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
	private Integer days;
	private Boolean parkingSlot;
	
	public Reservation() { }
	
	public Reservation(Long id, Guest guest, LocalDateTime expectedCheckinDate, LocalDateTime expectedCheckoutDate, LocalDateTime actualCheckinDate, 
					   LocalDateTime actualCheckoutDate, Integer days, Boolean parkingSlot) {
		this.id = id;
		this.guest = guest;
		this.expectedCheckinDate = expectedCheckinDate;
		this.expectedCheckoutDate = expectedCheckoutDate;
		this.actualCheckinDate = actualCheckinDate;
		this.actualCheckoutDate = actualCheckoutDate;
		this.days = days;
		this.parkingSlot = parkingSlot;
	}
	
	public Reservation(Guest guest, LocalDateTime expectedCheckinDate, LocalDateTime expectedCheckoutDate, LocalDateTime actualCheckinDate, 
					   LocalDateTime actualCheckoutDate, Integer days, Boolean parkingSlot) {
		this.guest = guest;
		this.expectedCheckinDate = expectedCheckinDate;
		this.expectedCheckoutDate = expectedCheckoutDate;
		this.actualCheckinDate = actualCheckinDate;
		this.actualCheckoutDate = actualCheckoutDate;
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
	
	public Long[] getActualDayCount() {
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
	
	public Integer getDayCount() {
		LocalDateTime startDate = this.expectedCheckinDate;
		LocalDateTime endDate = this.expectedCheckoutDate;
		Integer dayCount = 0;
		
		for (LocalDateTime date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			dayCount++;
		}
		return dayCount;
	}
}
