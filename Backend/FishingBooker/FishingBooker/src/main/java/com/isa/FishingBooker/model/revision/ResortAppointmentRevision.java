package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.ResortAppointment;

@Entity
public class ResortAppointmentRevision extends Revision {

	@OneToOne
	@JoinColumn(name = "resort_appointment_id")
	private ResortAppointment resortAppointment;
	
	public ResortAppointmentRevision() {}

	@Override
	public void setRelatedId(int id) {
		resortAppointment=new ResortAppointment(id);
	}

	@Override
	public int getRelatedId() {
		return resortAppointment.getId();
	}
}
