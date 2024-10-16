package com.gabriel.desafio.model;

import java.util.Calendar;
import java.util.Date;

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
	private Date expectedCheckinDate;
	private Date expectedCheckoutDate;
	private Date actualCheckinDate;
	private Date actualCheckoutDate;
	private Double dailyValue;
	private Integer days;
	private Boolean parkingSlot;
	
	public Reservation() { }
	
	public Reservation(Guest guest, Date expectedCheckinDate, Date expectedCheckoutDate, Date actualCheckinDate, 
					   Date actualCheckoutDate, Double dailyValue, Integer days, Boolean parkingSlot) {
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
	
	public Long[] getDayCount() {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		Long weekdayCount = 0L;
		Long weekendDayCount = 0L;
		
		startDate.setTime(this.actualCheckinDate);
		endDate.setTime(this.actualCheckoutDate);
		
		for (Calendar date = startDate; !date.after(endDate); date.add(Calendar.DAY_OF_MONTH, 1)) {
			int day = date.get(Calendar.DAY_OF_WEEK);
			if(day == Calendar.SATURDAY && day == Calendar.SUNDAY) {
				weekendDayCount++;
			} else {
				weekdayCount++;
			}
		}
		return new Long[] {weekdayCount, weekendDayCount};
	}
	
	public boolean isActualCheckoutWeekendDay() {
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.getActualCheckoutDate());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
	}
}
