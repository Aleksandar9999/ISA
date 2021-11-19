package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

public class Tutor extends User {
	
	private Integer id;
	private String bio;
	private Set<TutorService> services;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
