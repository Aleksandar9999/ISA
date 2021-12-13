package com.isa.FishingBooker.dto;

import com.isa.FishingBooker.model.User;

public class UserConfirmationDTO {

	private User user;
	private String comment;

	public UserConfirmationDTO() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
