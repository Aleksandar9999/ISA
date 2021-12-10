package com.isa.FishingBooker.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ResortAppointment extends Appointment{
	
	@ManyToOne
	@JoinColumn(name="resort_id")
	private Resort resort;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public ResortAppointment() {
		// TODO Auto-generated constructor stub
	}
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
