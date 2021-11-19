package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ResortOwner extends User {
	@OneToMany(mappedBy="resortOwner")
	private Set<Resort> resorts;
	
	public void setResorts(Set<Resort> resorts) {
		this.resorts = resorts;
	}

	public Set<Resort> getResorts() {
		return resorts;
	}

	public ResortOwner(){
		resorts=new HashSet<>();
	}
}
