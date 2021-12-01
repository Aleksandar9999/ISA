package com.isa.FishingBooker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class DeleteRequest {
	
	@Id
	@Column(name = "delete_request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String reason;
	private Status requestStatus;

	@OneToOne
	private User user;

	public DeleteRequest() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
