package com.gabriel.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
	
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
    
}