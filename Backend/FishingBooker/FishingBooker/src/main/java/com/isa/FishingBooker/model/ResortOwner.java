package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class ResortOwner extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "resortowner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Resort> resorts = new HashSet<Resort>();
	
	public ResortOwner() {
		super();
	}
	
	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.RESORTOWNER_ROLE);
	}
	
	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(Status.ADMIN_CONFIRMED);
	}
	
	public ResortOwner(String bio, Set<Resort> resorts) {
		this();
		this.resorts = resorts;
	}

	public ResortOwner(Integer idresortowner) {
		this();
		this.setId(idresortowner);
	}

	

	public void setResorts(Set<Resort> resorts) {
		this.resorts = resorts;
	}

	public void addResort(Resort resort) {
		resorts.add(resort);	}

	public Set<Resort> getResorts() {
		return resorts;
	}

}
	
