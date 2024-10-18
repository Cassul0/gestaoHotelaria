package com.gabriel.desafio.dto;

import com.gabriel.desafio.model.Payment;
import com.gabriel.desafio.model.Reservation;

public class PaymentDTO {
    private Reservation reservation;

    private Double dailyValue;
    private Double parkingFee;
    private Double lateCheckoutFee;
    private Double total;

    public PaymentDTO() { }
    
    public PaymentDTO(Payment payment) {
		super();
		this.reservation = payment.getReservation();
		this.dailyValue = payment.getDailyValue();
		this.parkingFee = payment.getParkingFee();
		this.lateCheckoutFee = payment.getLateCheckoutFee();
		this.total = payment.getTotal();
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
    
    public Payment build() {
    	return new Payment(this.reservation, 
    					   this.dailyValue,
    					   this.parkingFee, 
    					   this.lateCheckoutFee,
    					   this.total);
    }
}
