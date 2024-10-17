package com.gabriel.desafio.dto;

import com.gabriel.desafio.model.Guest;

public class GuestDTO {

    private Long id;
    private String name;
    private String document;
    private String phone;
    
    public GuestDTO(Long id, String name, String document, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.phone = phone;
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
	
	public Guest build() {
		return new Guest(this.name,
						 this.document,
						 this.phone);
	}

	public static GuestDTO build(Guest guest) {
		return new GuestDTO(guest.getId(),
						    guest.getName(),
						    guest.getDocument(),
						    guest.getPhone());
	}

	public static Guest build(GuestDTO guest) {
		return new Guest(guest.getId(),
					     guest.getName(),
					     guest.getDocument(),
					     guest.getPhone());
	}
	
}
