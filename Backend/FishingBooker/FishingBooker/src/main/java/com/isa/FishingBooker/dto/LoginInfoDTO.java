package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class LoginInfoDTO {
	private String email;
	private String password;
	private Status status;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginInfoDTO() {}
	
	public LoginInfoDTO(String email, String pass, Status status) {
		super();
		this.email = email;
		this.password = pass;
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
	
	
}
