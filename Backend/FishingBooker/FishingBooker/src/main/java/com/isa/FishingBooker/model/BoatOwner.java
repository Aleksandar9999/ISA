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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Period> boatAvailable;

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

	public void updateStandardPeriod(Period takenPeriodOfAppointment) {
		for (Period boatOwnerPeriod : boatAvailable) {
			try {
				boatOwnerPeriod.overlap(takenPeriodOfAppointment);
			} catch (PeriodOverlapException ex) {
				if (Period.isSameDate(boatOwnerPeriod.getStartDate(), takenPeriodOfAppointment.getStartDate())) {
					boatOwnerPeriod.setStartDate(takenPeriodOfAppointment.getEndDate());
				} else {
					Period newBeforePeriod = new Period(boatOwnerPeriod.getStartDate(),
							takenPeriodOfAppointment.getStartDate());
					Period newAfterPeriod = new Period(takenPeriodOfAppointment.getEndDate(), boatOwnerPeriod.getEndDate());
					boatAvailable.remove(boatOwnerPeriod);
					boatAvailable.add(newBeforePeriod);
					boatAvailable.add(newAfterPeriod);
				}
				break;
			}
		}
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

	public Set<Period> getAvailable() {
		return boatAvailable;
	}

	public void setAvailable(Set<Period> available) {
		this.boatAvailable = available;
	}

	public void addAvailablePeriod(Period period) {
		if (this.boatAvailable == null)
			this.boatAvailable = new HashSet<Period>();
		this.boatAvailable.add(period);
	}
	
	
	
	
}
