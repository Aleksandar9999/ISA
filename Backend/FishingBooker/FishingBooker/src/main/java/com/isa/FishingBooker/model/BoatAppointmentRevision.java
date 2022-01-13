package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BoatAppointmentRevision extends Revision {
	
	@OneToOne
	@JoinColumn(name = "boat_appointment_id")
	private BoatAppointment boatAppointment;
	
	public BoatAppointmentRevision() {}
	
	@Override
	public void setRelatedId(int id) {
		// TODO Auto-generated method stub
		boatAppointment.setId(id);
	}

	@Override
	public int getRelatedId() {
		// TODO Auto-generated method stub
		return boatAppointment.getId();
	}

}
