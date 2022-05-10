package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class CompletedAppointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private Appointment appointment;
	private double ownerProcentage;
	private double payedPrice;
	private double systemProcentage;

	public CompletedAppointment() {
	}

	public CompletedAppointment(Appointment appointment, double ownerProcentage, double payedPrice,
			double systemProcentage) {
		super();
		this.setAppointment(appointment);
		this.ownerProcentage = ownerProcentage;
		this.payedPrice = payedPrice;
		this.systemProcentage = systemProcentage;
	}

	public double getOwnerRevenue() {
		return this.payedPrice * (ownerProcentage / 100);
	}

	public double getSystemRevenue() {
		return this.payedPrice * (systemProcentage / 100);
	}

	public double getPayedPrice() {
		return this.payedPrice;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
