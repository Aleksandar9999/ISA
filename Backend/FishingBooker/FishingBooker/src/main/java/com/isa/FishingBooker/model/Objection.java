package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Objection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
	private String objection;
	@OneToOne
	private User creator;
	@OneToOne
	private Admin adminResponded;
	private String response;
	public Admin getAdminResponded() {
		return adminResponded;
	}

	public void setAdminResponded(Admin adminResponded) {
		this.adminResponded = adminResponded;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getObjection() {
		return objection;
	}

	public void setObjection(String objection) {
		this.objection = objection;
	}

	public String getUserEmail() {
		return creator.getEmail();
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
