package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TutorServiceAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name = "tutor_service_id")
	private TutorService tutorService;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public TutorServiceAppointment() {
	}

	public TutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
