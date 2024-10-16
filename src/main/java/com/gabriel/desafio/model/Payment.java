package com.gabriel.desafio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Reservation reserva;

    private Double valorDiaria;
    private Double taxaEstacionamento;
    private Double taxaCheckoutAtrasado;
    private Double valorTotal;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReserva() {
        return reserva;
    }

    public void setReserva(Reservation reserva) {
        this.reserva = reserva;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Double getTaxaEstacionamento() {
        return taxaEstacionamento;
    }

    public void setTaxaEstacionamento(Double taxaEstacionamento) {
        this.taxaEstacionamento = taxaEstacionamento;
    }

    public Double getTaxaCheckoutAtrasado() {
        return taxaCheckoutAtrasado;
    }

    public void setTaxaCheckoutAtrasado(Double taxaCheckoutAtrasado) {
        this.taxaCheckoutAtrasado = taxaCheckoutAtrasado;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}