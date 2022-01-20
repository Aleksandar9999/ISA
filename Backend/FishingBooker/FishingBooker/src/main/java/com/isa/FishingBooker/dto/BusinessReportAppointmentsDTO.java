package com.isa.FishingBooker.dto;

import java.util.List;

import com.isa.FishingBooker.model.Appointment;

public class BusinessReportAppointmentsDTO<T extends Appointment> {
	private List<T> appointments;

	public BusinessReportAppointmentsDTO(List<T> appointments) {
		this.appointments = appointments;
	}

	public List<T> getAppointments() {
		return appointments;
	}

	public double getSumPrice() {
		return appointments.stream().mapToDouble(Appointment::getPrice).sum();
	}

	public long getNumberOfCancelledAppointments() {
		return appointments.stream().filter(app -> app.getUser() == null).count();
	}

	public long getNumberOfAppointments() {
		return appointments.stream().count();
	}
}
