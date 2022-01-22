package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class DeleteRequestDTO {
	private int id;
	private String reason;
	private Status requestStatus;
	private int userId;
	private int adminRespondedId;
	
	public int getAdminRespondedId() {
		return adminRespondedId;
	}

	public void setAdminRespondedId(int adminRespondedId) {
		this.adminRespondedId = adminRespondedId;
	}

	public DeleteRequestDTO() {
	}

	public DeleteRequestDTO(int id, int userId) {
		this.id = id;
		this.userId = userId;
	}

	public DeleteRequestDTO(int id, int userId, Status requestStatus) {
		this(id,userId);
		this.requestStatus=requestStatus;
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
