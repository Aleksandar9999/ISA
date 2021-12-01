package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoatAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
	
	
}
