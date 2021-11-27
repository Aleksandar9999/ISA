package com.isa.FishingBooker.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ResortAppointment extends Appoinment{
	
	@ManyToOne
	@JoinColumn(name="resort_id")
	private Resort resort;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Resort getResort() {
		return resort;
	}
	public void setResort(Resort resort) {
		this.resort = resort;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
