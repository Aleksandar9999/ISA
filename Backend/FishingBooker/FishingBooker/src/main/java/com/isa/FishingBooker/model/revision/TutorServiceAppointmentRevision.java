package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.TutorServiceAppointment;

@Entity
public class TutorServiceAppointmentRevision extends Revision {
	
	@OneToOne
	@JoinColumn(name = "tutor_service_appointment_id")
	private TutorServiceAppointment tutorServiceAppointment;
	
	public TutorServiceAppointmentRevision() {}

	@Override
	public void setRelatedId(int id) {
		// TODO Auto-generated method stub
		tutorServiceAppointment.setId(id);
	}

	@Override
	public int getRelatedId() {
		// TODO Auto-generated method stub
		return tutorServiceAppointment.getId();
	}

}
