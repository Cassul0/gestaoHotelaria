package com.gabriel.desafio.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
	
	public static final Double WEEK_DAILY_VALUE = 120.00;
	public static final Double WEEKEND_DAILY_VALUE = 180.00;
	public static final Double WEEKDAY_PARKING_VALUE = 15.00;
	public static final Double WEEKEND_PARKING_VALUE = 20.00;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Reservation reservation;
    private Double dailyValue;
    private Double parkingFee;
    private Double lateCheckoutFee;
    private Double total;

    public Payment() { }
    
    public Payment(Reservation reservation, Double dailyValue, Double parkingFee, Double lateCheckoutFee, Double total) {
    	this.reservation = reservation;
    	this.dailyValue = dailyValue;
    	this.parkingFee = parkingFee;
    	this.lateCheckoutFee = lateCheckoutFee;
    	this.total = total;
    }
    
    public Payment(Reservation reservation, Long[] dayCount, boolean isWeekend) {
    	this.reservation = reservation;
    	this.dailyValue = this.getDailyValueFromCheckoutReservation(dayCount);
    	this.parkingFee = this.getParkingSlotFee(dayCount);
    	this.lateCheckoutFee = this.getLateCheckoutFee(isWeekend);
    	this.total = this.dailyValue + this.parkingFee + this.lateCheckoutFee;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Double getDailyValue() {
		return dailyValue;
	}

	public void setDailyValue(Double dailyValue) {
		this.dailyValue = dailyValue;
	}

	public Double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(Double parkingFee) {
		this.parkingFee = parkingFee;
	}

	public Double getLateCheckoutFee() {
		return lateCheckoutFee;
	}

	public void setLateCheckoutFee(Double lateCheckoutFee) {
		this.lateCheckoutFee = lateCheckoutFee;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
    
	public Double getDailyValueFromCheckoutReservation(Long[] dayCount ) {
		return WEEK_DAILY_VALUE * dayCount[0] + WEEKEND_DAILY_VALUE * dayCount[1];
	}
	
	public Double getParkingSlotFee(Long[] dayCount ) {
		return WEEKDAY_PARKING_VALUE * dayCount[0] + WEEKEND_PARKING_VALUE * dayCount[1];
	}
	
	public Double getLateCheckoutFee(boolean isWeekend) {
		return isWeekend ? WEEKEND_DAILY_VALUE * 0.5 : WEEK_DAILY_VALUE * 0.5;
	}
}