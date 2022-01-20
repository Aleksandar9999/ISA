package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;

public class TutorServiceDTO {
	private Integer id;
	private String name;
	private String description;
	private int maxPerson;
	private String rules;
	private String fishingEquipment;
	private double cancelProcentage;
	private Address address;
	private String tutorBio;
	private Tutor tutor;
	private String extrasServices;
	private Status status;

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

	public double getCancelProcentage() {
		return cancelProcentage;
	}

	public void setCancelProcentage(double cancelProcentage) {
		this.cancelProcentage = cancelProcentage;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTutorBio() {
		return tutorBio;
	}

	public void setTutorBio(String tutorbio) {
		this.tutorBio = tutorbio;
	}

	public int getTutorId() {
		return this.tutor.getId();
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public String getExtrasServices() {
		return extrasServices;
	}

	public void setExtrasServices(String extrasServices) {
		this.extrasServices = extrasServices;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public void setTutor(int id) {this.tutor=new Tutor(id);}
}
