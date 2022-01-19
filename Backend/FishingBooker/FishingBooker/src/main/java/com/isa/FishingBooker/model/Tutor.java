package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class Tutor extends User {

	private static final long serialVersionUID = -4342071307816418991L;
	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TutorService> services = new HashSet<TutorService>();

	public Tutor() {
		super();
	}

	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.TUTOR_ROLE);
	}

	public Tutor(String bio, Set<TutorService> services) {
		this();
		this.services = services;
	}

	public Tutor(Integer idtutor) {
		this();
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

	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(Status.ADMIN_CONFIRMED);
	}
}
