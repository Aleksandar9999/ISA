package com.isa.FishingBooker.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends User {
	private String bio;
	private List<TutorService> services;
	public Tutor() {
		services=new ArrayList<>();
	}

	public void addSrervice(TutorService service){
		services.add(service);
	}

	public List<TutorService> getServices(){
		return services;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
