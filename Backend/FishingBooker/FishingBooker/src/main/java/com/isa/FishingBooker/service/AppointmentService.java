package com.isa.FishingBooker.service;

import java.util.List;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;

public interface AppointmentService extends Service<Appointment> {
	public List<ResortAppointment> getResortApointments();
	public List<BoatAppointment> getBoatApointments();
	public List<TutorServiceAppointment> getTutorServiceApointments();
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByTutor(int id);
	public void addNewTutorServiceAppointment(TutorServiceAppointment app);
	public List<Appointment>getPendingApointments(String email);
}
