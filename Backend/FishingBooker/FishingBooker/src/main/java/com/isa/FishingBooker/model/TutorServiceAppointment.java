package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TutorServiceAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name = "tutor_service_id")
	private TutorService tutorService;

	public TutorServiceAppointment() {
	}

	public TutorServiceAppointment(int id) {
		super(id);
	}

	public TutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}
	
	@JsonIgnore
	public int getTutorId() {
		return tutorService.getTutor().getId();
	}

	@Override
	public User getOwner() {
		return tutorService.getTutor();
	}
}
