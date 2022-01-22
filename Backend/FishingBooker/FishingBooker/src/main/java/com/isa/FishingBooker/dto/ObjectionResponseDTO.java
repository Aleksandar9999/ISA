package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.Objection;

public class ObjectionResponseDTO extends ObjectionDTO {
	private String response;

	public ObjectionResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
