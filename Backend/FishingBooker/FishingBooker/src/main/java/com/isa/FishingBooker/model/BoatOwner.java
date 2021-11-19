package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

public class BoatOwner  extends User {
	private Integer id;
	private Set<Boat> boats;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Boat> getBoats() {
		return boats;
	}

	public void setBoats(Boat boat) {
		boats.add(boat);
	}

	public BoatOwner(){
		boats=new HashSet<>();
	}
}
