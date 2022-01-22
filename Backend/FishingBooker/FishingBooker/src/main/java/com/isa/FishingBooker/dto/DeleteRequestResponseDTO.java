package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Status;

public class DeleteRequestResponseDTO {
	private String response;
	private int id;
	private Status requestStatus;
	private int userId;
	public DeleteRequestResponseDTO() {
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public DeleteRequestDTO getDeleteReguestDto() {
		return new DeleteRequestDTO(id,userId,requestStatus);
	}
	
}
