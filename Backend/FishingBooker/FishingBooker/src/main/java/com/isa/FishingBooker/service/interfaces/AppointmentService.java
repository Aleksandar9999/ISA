package com.isa.FishingBooker.service.interfaces;

import java.sql.Date;

import java.util.List;

import com.isa.FishingBooker.model.*;

public interface AppointmentService extends Service<Appointment> {
	public void finishAppointment(Integer id);
	public List<CompletedAppointment> getAllCompletedAppointmentsInPeriod(Date start,Date end);
	public List<CompletedAppointment> getAllCompletedAppointmentsInPeriodByTutorId(int tutorId,Date start,Date end);

	public List<Appointment> getAllInPeriod(Date start,Date end);
	public List<ResortAppointment> getResortApointments();
	public List<Appointment>getPendingApointments();
	public void deleteResortAppointments(int resortId);
	

	
	public List<TutorServiceAppointment> getTutorServiceApointments();
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByTutor(int id);
	public void addNewTutorServiceAppointment(TutorServiceAppointment app);
	public void addNewTutorServiceAppointmentByTutor(TutorServiceAppointment app,boolean validateUser);
	public void addNewTutorServiceAppointmentFromDiscount(TutorServiceAppointment app);
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, Date start, Date end);
	public String makeTutorServiceReservation(TutorServiceAppointment appointment);
	public List<TutorServiceAppointment> getAllTutorServiceAppoints();
	public List<TutorServiceAppointment> getAllPendingByTutorServiceId(int id);
	
	public List<BoatAppointment> getBoatApointments();
	public List<BoatAppointment> getAllBoatAppointmentsByBoat(int id);
	public void addNewBoatAppointment(BoatAppointment app);
	public void addNewBoatAppointmentByBoatOwner(BoatAppointment app,boolean validateUser);
	public void addNewBoatAppointmentFromDiscount(BoatAppointment app);
	public List<BoatAppointment> getAllByBoatAndPeriod(int boatId,Date start,Date end);
	public String makeBoatReservation(BoatAppointment appointment);
	public List<BoatAppointment> getAllBoatAppoints();
	public List<BoatAppointment> getAllPendingByBoatId(int id);
	public List<BoatAppointment> getAllByBoatOwnerAndPeriod(int boatOwnerId, Date start, Date end);
	public List<BoatAppointment> getAllBoatAppointmentsByBoatOwner(int id);
	public List<CompletedAppointment> getAllCompletedAppointmentsInPeriodByBoatOwnerId(int boatOwnerId,Date start,Date end);
	
	
	public String makeResortReservation(ResortAppointment appointment);
	public String cancelReservation(Integer id);
	public List<ResortAppointment> getAllResortAppoints();

	public String makeQuickReservation(Integer id);
	public List<Appointment> getOldAppointments();
	public List<String> getAdditionalServicesForBoat(Integer boatId);
	public List<java.util.Date> getBoatPeriods(Integer boatId);

}
