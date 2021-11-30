package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tutor extends User {

	private String bio;
	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TutorService> services = new HashSet<TutorService>();

	//TODO:  Da li da sada napravim klasu koja nasljeduje period koja ima dodatno polje tutor?
	@OneToMany(mappedBy = "")
	private Set<Period> availabilityPeriods;
	public Tutor() {
	}

	public Tutor(String bio, Set<TutorService> services) {
		super();
		this.bio = bio;
		this.services = services;
	}

	public Tutor(Integer idtutor) {
		this.setId(idtutor);
	}

	public void setServices(Set<TutorService> services) {
		this.services = services;
	}

	public void addService(TutorService service) {
		services.add(service);
	}

	public Set<TutorService> getServices() {
		return services;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
