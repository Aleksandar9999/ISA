package com.isa.FishingBooker.dto;

import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.ReportType;
import com.isa.FishingBooker.model.Status;

public class AppointmentReportDTO {
	private Integer id;
	private int appointmentId;
	private String comment;
	private ReportType type;
	private Status status;

	public AppointmentReportDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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
}
