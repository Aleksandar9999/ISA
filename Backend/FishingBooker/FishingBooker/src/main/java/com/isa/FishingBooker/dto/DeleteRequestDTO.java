package com.isa.FishingBooker.dto;

import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;

public class DeleteRequestDTO {
	private int id;
	private String reason;
	private Status requestStatus;
	private int userId;
	public DeleteRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Status getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
