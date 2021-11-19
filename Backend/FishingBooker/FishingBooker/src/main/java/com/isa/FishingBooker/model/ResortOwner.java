package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

public class ResortOwner extends User {
	private Integer id;
	private Set<Resort> resorts;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
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
