package com.isa.FishingBooker.service;

import java.util.List;

import com.isa.FishingBooker.model.Appoinment;

public interface AppointmentService extends Service<Appoinment> {
	public List<Appoinment> getResortApointments();
	public List<Appoinment> getBoatApointments();
	public List<Appoinment> getTutorServiceApointments();
}
