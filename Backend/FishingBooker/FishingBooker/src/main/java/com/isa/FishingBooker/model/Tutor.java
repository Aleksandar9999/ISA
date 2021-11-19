package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Tutor extends User {
	
	private String bio;
	@OneToMany
	private Set<TutorService> services;

	public Tutor() {
		services=new HashSet<>();
	}	
	
	public void setServices(Set<TutorService> services) {
		this.services = services;
	}

	public void addSrervice(TutorService service){
		services.add(service);
	}

	public Set<TutorService> getServices(){
		return services;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
