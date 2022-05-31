package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Status;

public class ResortDTO {
	private Integer id;
	private String name;
	private Address resortAddress;
	private String description;
	private int numOfRooms;
	private int numOfBeds;
	private String rules;
	private int rate;
	private Status status;
	private String extrasServices;
	private int resortOwnerId;
	
	ResortDTO(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getResortAddress() {
		return resortAddress;
	}

	public void setResortAddress(Address resortAddress) {
		this.resortAddress = resortAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getExtrasServices() {
		return extrasServices;
	}

	public void setExtrasServices(String extrasServices) {
		this.extrasServices = extrasServices;
	}

	public int getResortOwnerId() {
		return resortOwnerId;
	}

	public void setResortOwnerId(int resortOwnerId) {
		this.resortOwnerId = resortOwnerId;
	}
	
	
}
