package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoatAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
	public BoatAppointment() {
	}
	
	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	@Override
	public User getOwner() {
		return boat.getBoatOwner();
	}
	
}
