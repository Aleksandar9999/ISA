package com.isa.FishingBooker.dto;

import java.util.List;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentStatus;

public class BusinessReportAppointmentsDTO<T extends Appointment> {
	private List<T> appointments;

	public BusinessReportAppointmentsDTO(List<T> appointments) {
		this.appointments = appointments;
	}

	public List<T> getAppointments() {
		return appointments;
	}

	public double getSumPrice() {
		double sum = appointments.stream().filter(app -> app.getStatus().equals(AppointmentStatus.SUCCESSFUL))
				.mapToDouble(Appointment::getPrice).sum();
		sum += appointments.stream().filter(app -> app.getStatus().equals(AppointmentStatus.CANCELED))
				.mapToDouble(Appointment::getPriceCanceled).sum();
		return sum;
	}

	public long getNumberOfCancelledAppointments() {
		return appointments.stream().filter(app -> app.getStatus().equals(AppointmentStatus.CANCELED)).count();
	}
	public long getNumberOfSuccessfulAppointments() {
		return appointments.stream().filter(app -> app.getStatus().equals(AppointmentStatus.SUCCESSFUL)).count();
	}
	public long getNumberOfAppointments() {
		return appointments.stream().count();
	}
}
