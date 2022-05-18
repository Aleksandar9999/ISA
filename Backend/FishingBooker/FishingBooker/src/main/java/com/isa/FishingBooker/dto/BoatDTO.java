package com.isa.FishingBooker.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Status;

public class BoatDTO {
	private Integer id;
	private String name;
	private String typeOfBoat;
	private String length;
	private String engineId;
	private String enginePower;
	private int maxSpeed;
	private String navigationEquipment;
	private Address boatAddress;
	private String description;
	private int maxPerson;
	private String rules;
	private String fishingEquipment;
	private double cancelPercentage;
	private int rate;
	private Status status;
	private String extrasServices;
	private int boatOwnerId;
	
	public BoatDTO() {
		// TODO Auto-generated constructor stub
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

	public String getTypeOfBoat() {
		return typeOfBoat;
	}

	public void setTypeOfBoat(String typeOfBoat) {
		this.typeOfBoat = typeOfBoat;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public String getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(String navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public Address getBoatAddress() {
		return boatAddress;
	}

	public void setBoatAddress(Address boatAddress) {
		this.boatAddress = boatAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public double getCancelPercentage() {
		return cancelPercentage;
	}

	public void setCancelPercentage(double cancelPercentage) {
		this.cancelPercentage = cancelPercentage;
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

	public int getBoatOwnerId() {
		return boatOwnerId;
	}

	public void setBoatOwnerId(int boatOwnerId) {
		this.boatOwnerId = boatOwnerId;
	}
	
	
}
