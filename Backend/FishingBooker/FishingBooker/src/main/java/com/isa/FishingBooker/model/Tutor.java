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

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Period> availabilityPeriods = new HashSet<Period>();

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

	public Set<Period> getAvailabilityPeriods() {
		return availabilityPeriods;
	}

	public void setAvailabilityPeriods(Set<Period> availabilityPeriods) {
		this.availabilityPeriods = availabilityPeriods;
	}

	public void addPeriod(Period period) {
		this.availabilityPeriods.add(period);
	}
}
