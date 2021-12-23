package com.isa.FishingBooker.dto;

public class ComplaintDTO {
	private int id;
	private int appellantId;
	private int appelleeId;
	private int appelleeServiceId;
	private String complaintText;
	private String adminResponse;
	private String type;
	
	public ComplaintDTO() {
	}
	
	public int getAppelleeServiceId() {
		return appelleeServiceId;
	}

	public void setAppelleeServiceId(int appelleeServiceId) {
		this.appelleeServiceId = appelleeServiceId;
	}
	
	public int getAppellantId() {
		return appellantId;
	}

	public void setAppellantId(int appellantId) {
		this.appellantId = appellantId;
	}

	public int getAppelleeId() {
		return appelleeId;
	}


	public void setAppelleeId(int appelleeId) {
		this.appelleeId = appelleeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaint) {
		this.complaintText = complaint;
	}

	public String getAdminResponse() {
		return adminResponse;
	}

	public void setAdminResponse(String adminResponse) {
		this.adminResponse = adminResponse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
}
