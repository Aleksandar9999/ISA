package com.isa.FishingBooker.service;

import java.sql.Date;

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
	public void addNewTutorServiceAppointmentByTutor(TutorServiceAppointment app,boolean validateUser);
	public List<Appointment>getPendingApointments(String email);
	public List<Appointment>getPendingApointments();
	public void deleteResortAppointments(int resortId);
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, Date start, Date end);
	public String makeBoatReservation(BoatAppointment appointment);
	public String makeResortReservation(ResortAppointment appointment);
	public String makeTutorServiceReservation(TutorServiceAppointment appointment);
	public String cancelReservation(Integer id);
	public List<BoatAppointment> getAllBoatAppoints();
	public List<ResortAppointment> getAllResortAppoints();
	public List<TutorServiceAppointment> getAllTutorServiceAppoints();
	public String makeQuickReservation(Integer id);
	public List<Appointment> getOldAppointments();
	public List<String> getAdditionalServicesForBoat(Integer boatId);
	public List<java.util.Date> getBoatPeriods(Integer boatId);
}
