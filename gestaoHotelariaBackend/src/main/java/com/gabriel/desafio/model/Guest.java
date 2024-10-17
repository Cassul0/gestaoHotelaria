package com.gabriel.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gabriel.desafio.dto.GuestDTO;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    private String phone;
	
    public Guest() { }
    
    public Guest(Long id, String name, String document, String phone) {
    	this.id = id;
    	this.name = name;
    	this.document = document;
    	this.phone = phone;
    }
    
    public Guest(String name, String document, String phone) {
    	this.name = name;
    	this.document = document;
    	this.phone = phone;
    }
    
    public Guest(Guest guest) {
    	this.id = guest.getId();
    	this.name = guest.getName();
    	this.document = guest.getDocument();
    	this.phone = guest.getPhone();
    }
    
    public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
