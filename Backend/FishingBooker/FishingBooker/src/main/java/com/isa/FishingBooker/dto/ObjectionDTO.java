package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Admin;

public class ObjectionDTO {
	private Integer id;
	private int appointmentId;
	private String objection;
	private String userEmail;
	private int adminRespondedId;
	public ObjectionDTO() {
		// TODO Auto-generated constructor stub
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
	public int getAdminRespondedId() {
		return adminRespondedId;
	}
	public void setAdminRespondedId(int adminRespondedId) {
		this.adminRespondedId = adminRespondedId;
	}
}
