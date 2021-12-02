package com.isa.FishingBooker.service;

import java.util.List;

import com.isa.FishingBooker.model.Appoinment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;

public interface AppointmentService extends Service<Appoinment> {
	public List<ResortAppointment> getResortApointments();
	public List<BoatAppointment> getBoatApointments();
	public List<TutorServiceAppointment> getTutorServiceApointments();
	public List<Appoinment>getPendingApointments(String email);
}
