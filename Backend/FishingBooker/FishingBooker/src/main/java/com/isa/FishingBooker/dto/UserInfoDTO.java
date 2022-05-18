package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Status;

public class UserInfoDTO {
	private Integer id;
	private String email;
	private String name;
	private String surname;
	private Address address;
	private String phoneNumber;
	private Status status;
	private String className;
	private boolean shouldApprove;
	private double points;
	private String registrationReason;
	public UserInfoDTO() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isShouldApprove() {
		return shouldApprove;
	}

	public void setShouldApprove(boolean shouldApprove) {
		this.shouldApprove = shouldApprove;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public void setRegistrationReason(String registrationReason) {
		this.registrationReason = registrationReason;
	}
	
	
}
