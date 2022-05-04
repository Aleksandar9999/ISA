package com.isa.FishingBooker.dto;

import java.util.List;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentStatus;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.SystemData;

public class BusinessReportAppointmentsAdminDTO<T extends Appointment> extends BusinessReportAppointmentsDTO<T> {

	private List<SystemData> procentages;

	public BusinessReportAppointmentsAdminDTO(List<T> appointments) {
		super(appointments);
	}

	public BusinessReportAppointmentsAdminDTO(List<T> appointments, List<SystemData> procentages) {
		super(appointments);
		this.procentages = procentages;
	}

	@Override
	public double getRevenue() {
		double sum = 0;
		for (T appointment : appointments) {
			if (appointment.getStatus().equals(AppointmentStatus.SUCCESSFUL)) {
				sum += appointment.getPrice() * (findProcentage(appointment.getPeriod()) / 100);
			}
		}
		return sum;
	}

	private double findProcentage(Period period) {
		for (SystemData procentage : procentages) {
			if (procentage.getValid().inPeriod(period.getStartDate())) {
				return procentage.getProcentage();
			}
		}
		return findCurrentlyActive();
	}

	private double findCurrentlyActive() {
		for (SystemData procentage : procentages) {
			if (procentage.getValid().getEndDate() == null)
				return procentage.getProcentage();
		}
		return 0;
	}
}
