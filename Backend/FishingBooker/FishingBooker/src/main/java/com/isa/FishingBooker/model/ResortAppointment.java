package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ResortAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name = "resort_id")
	private Resort resort;

	public ResortAppointment() {
		// TODO Auto-generated constructor stub
	}

	public ResortAppointment(int id) {
		super(id);
	}

	public Resort getResort() {
		return resort;
	}

	public void setResort(Resort resort) {
		this.resort = resort;
	}

	@Override
	public User getOwner() {
		return resort.getResortOwner();
	}

}
