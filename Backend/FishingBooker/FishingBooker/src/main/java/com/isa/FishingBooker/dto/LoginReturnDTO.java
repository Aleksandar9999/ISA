package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class LoginReturnDTO {
	private Integer id;
	private String email;
	private Status status;
	
	public LoginReturnDTO() {}
	
	public LoginReturnDTO(String email, Status status) {
		this.email = email;
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
