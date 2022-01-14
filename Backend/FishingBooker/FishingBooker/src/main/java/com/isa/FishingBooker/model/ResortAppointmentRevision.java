package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.revision.Revision;

@Entity
public class ResortAppointmentRevision extends Revision {

	@OneToOne
	@JoinColumn(name = "resort_appointment_id")
	private ResortAppointment resortAppointment;
	
	public ResortAppointmentRevision() {}

	@Override
	public void setRelatedId(int id) {
		// TODO Auto-generated method stub
		resortAppointment.setId(id);
	}

	@Override
	public int getRelatedId() {
		// TODO Auto-generated method stub
		return resortAppointment.getId();
	}
}
