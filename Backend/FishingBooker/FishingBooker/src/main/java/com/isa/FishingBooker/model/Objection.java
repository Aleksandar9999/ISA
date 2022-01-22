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
	private String userEmail;

	@OneToOne
	@JoinColumn(nullable = true)
	private Admin adminResponded;

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
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
