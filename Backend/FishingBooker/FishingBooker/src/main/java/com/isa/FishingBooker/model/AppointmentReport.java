package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AppointmentReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private Appointment appointment;
	private String comment;
	private ReportType type;
	private Status status;
	@OneToOne
	private Admin adminResponded;
	private String response;

	public AppointmentReport() {
	}

	@JsonIgnore
	public User getServiceOwner() {
		return appointment.getOwner();
	}

	@JsonIgnore
	public User getClient() {
		return appointment.getUser();
	}

	public void setStatusAndTypeOfReport(Status status, ReportType type) {
		this.setStatus(status);
		this.setType(type);
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ReportType getType() {
		return type;
	}

	public void setType(ReportType type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Admin getAdminResponded() {
		return adminResponded;
	}

	public void setAdminResponded(Admin adminResponded) {
		this.adminResponded = adminResponded;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public AppointmentReport setAppointmentId(int appointmentId) {
		this.appointment = new Appointment(appointmentId);
		return this;
	}
}
