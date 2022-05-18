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
	private double ownerPercentage;
	private double payedPrice;
	private double systemPercentage;
	private double canceledPercentage;
	public CompletedAppointment() {
	}

	public CompletedAppointment(Appointment appointment, double ownerPercentage, double payedPrice,
								double systemPercentage,double canceledPercentage) {
		super();
		this.setAppointment(appointment);
		this.ownerPercentage = ownerPercentage;
		this.payedPrice = payedPrice;
		this.systemPercentage = systemPercentage;
	}

	public double getCanceledAppointmentOwnerRevenue(){return this.payedPrice*(canceledPercentage / 100);}

	public double getOwnerRevenue() {
		return this.payedPrice * (ownerPercentage / 100);
	}

	public double getSystemRevenue() {
		if(appointment.getStatus().equals(AppointmentStatus.CANCELED)) return 0;
		return this.payedPrice * (systemPercentage / 100);
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
