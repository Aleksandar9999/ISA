package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.BoatAppointment;

@Entity
public class BoatAppointmentRevision extends Revision {
	
	@OneToOne
	@JoinColumn(name = "boat_appointment_id")
	private BoatAppointment boatAppointment;
	
	public BoatAppointmentRevision() {}

	@Override
	public String getOwnerEmail() {
		return boatAppointment.getOwner().getEmail();
	}

	@Override
	public void setRelatedId(int id) {
		boatAppointment=new BoatAppointment(id);
	}

	@Override
	public int getRelatedId() {
		// TODO Auto-generated method stub
		return boatAppointment.getId();
	}

}
