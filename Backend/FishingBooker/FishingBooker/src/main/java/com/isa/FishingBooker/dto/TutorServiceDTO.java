package com.isa.FishingBooker.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Extras;
import com.isa.FishingBooker.model.Photo;
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
	private int tutorId;
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
		return tutorId;
	}
	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}

}
