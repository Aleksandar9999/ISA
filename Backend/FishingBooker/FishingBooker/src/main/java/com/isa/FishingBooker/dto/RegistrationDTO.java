package com.isa.FishingBooker.dto;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.model.Address;

public class RegistrationDTO {
	private String email;
	private String password;
	private String name;
	private String surname;
	private Address address;
	private String phoneNumber;
	private String registrationReason;

	public RegistrationDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public void setRegistrationReason(String registrationReason) {
		this.registrationReason = registrationReason;
	}

}
