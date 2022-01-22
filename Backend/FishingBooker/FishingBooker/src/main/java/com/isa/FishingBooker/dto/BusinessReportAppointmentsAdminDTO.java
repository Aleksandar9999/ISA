package com.isa.FishingBooker.dto;

import java.util.List;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentStatus;

public class BusinessReportAppointmentsAdminDTO<T extends Appointment> extends BusinessReportAppointmentsDTO<T>{

	private double systemProcentage;
	public BusinessReportAppointmentsAdminDTO(List<T> appointments) {
		super(appointments);
	}
	public BusinessReportAppointmentsAdminDTO(List<T> appointments,double systemProcentage) {
		super(appointments);
		this.systemProcentage=systemProcentage;
	}
	@Override
	public double getRevenue() {
		double sum = appointments.stream().filter(app -> app.getStatus().equals(AppointmentStatus.SUCCESSFUL))
				.mapToDouble(Appointment::getPrice).sum();
		return sum*(systemProcentage/100);
	}
}
