package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.isa.FishingBooker.exceptions.PeriodOverlapException;
@Entity
public class BoatOwner  extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "boatowner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Boat> boats = new HashSet<Boat>();
	
	

	public BoatOwner() {
		super();
	}
	
	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.BOATOWNER_ROLE);
	}
	
	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(Status.ADMIN_CONFIRMED);
	}
	
	public BoatOwner(String bio, Set<Boat> boats) {
		this();
		this.boats = boats;
	}

	public BoatOwner(Integer idboatowner) {
		this();
		this.setId(idboatowner);
	}

	

	public void setBoats(Set<Boat> boats) {
		this.boats = boats;
	}

	public void addBoat(Boat boat) {
		boats.add(boat);
	}

	public Set<Boat> getBoats() {
		return boats;
	}


	
	
	
	
}
