package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class LoginInfoDTO {
	private String email;
	private String pass;
	private Status status;
	
	public LoginInfoDTO() {}
	
	public LoginInfoDTO(String email, String pass, Status status) {
		super();
		this.email = email;
		this.pass = pass;
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
