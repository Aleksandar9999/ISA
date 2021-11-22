package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class BoatOwner  extends User {

	@OneToMany(fetch=FetchType.EAGER)
	private Set<Boat> boats;
	

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
