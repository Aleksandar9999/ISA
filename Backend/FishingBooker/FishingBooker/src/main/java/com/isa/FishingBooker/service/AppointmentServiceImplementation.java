package com.isa.FishingBooker.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.exceptions.UserAppointmentInProgressException;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.TutorServiceRepository;

@Service
public class AppointmentServiceImplementation extends CustomServiceAbstract<Appointment> implements AppointmentService {
	
	@Autowired
	private TutorServicesService tutorServicesService;
		
	public List<ResortAppointment> getResortApointments(){
		return ((AppointmentRepository)repository).getAllResortAppoints();
	}
	
	public List<BoatAppointment> getBoatApointments(){
		return ((AppointmentRepository)repository).getAllBoatAppoints();
	}
	
	public List<TutorServiceAppointment> getTutorServiceApointments(){
		return ((AppointmentRepository)repository).getAllTutorServiceAppointments();
	}

	@Override
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByTutor(int id) {
		return ((AppointmentRepository)repository).getAllAppointmentsByTutor(id);
	}

	@Override
	public void addNewTutorServiceAppointment(TutorServiceAppointment app) {
		TutorService tutorService=tutorServicesService.getById(app.getTutorService().getId());
		app.setTutorService(tutorService);
		validateNewTutorServiceAppointment(app);
		app.setPrice(tutorService.calculatePrice((int) app.getDuration()));
		super.addNew(app);
	}

	@Override
	public void addNewTutorServiceAppointment(TutorServiceAppointment app, boolean validateUser) {
		if(validateUser) {
			validateUseCurrentAppointment(app.getUser().getId());
		}
		this.addNewTutorServiceAppointment(app);
	}
	
	private void validateUseCurrentAppointment(Integer id) {
	    Date currentDate= new Date(System.currentTimeMillis());
	    Period currentPeriod= new Period(currentDate, currentDate);
		for (TutorServiceAppointment appointment : ((AppointmentRepository)repository).getAllTutorServiceAppointmentsByUser(id)) {
			try{
				validateUserAppointmentOverlap(currentPeriod, appointment);
			}catch (PeriodOverlapException e) {
				break;
			}catch (UserAppointmentInProgressException e) {
				throw e;
			}
		}
	}

	private void validateUserAppointmentOverlap(Period currentPeriod, TutorServiceAppointment appointment) {
		createPeriod(appointment).overlap(currentPeriod);
		throw new UserAppointmentInProgressException();
	}

	//TODO:REFACTOR
	@Override
	public List<Appointment> getPendingApointments(String email) {
		List<Appointment> appointments = new ArrayList<Appointment>();
		Date thisMoment = new Date(System.currentTimeMillis());
		for(BoatAppointment a : ((AppointmentRepository)repository).getAllBoatAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(ResortAppointment a : ((AppointmentRepository)repository).getAllResortAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(TutorServiceAppointment a : ((AppointmentRepository)repository).getAllTutorServiceAppointments()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		return appointments;
	}

	@Override
	public void deleteResortAppointments(int resortId) {
		((AppointmentRepository)repository).deleteResortAppointments(resortId);
	}

	private void validateNewTutorServiceAppointment(TutorServiceAppointment newAppointment) {
		List<TutorServiceAppointment> allInCommingAppointmentsByTutor = this.getAllInCommingAppointmentsByTutor(newAppointment.getTutorId());
		for (TutorServiceAppointment tutorServiceAppointment : allInCommingAppointmentsByTutor) {
			createPeriod(newAppointment).overlap(createPeriod(tutorServiceAppointment));
		}
	}

	private Period createPeriod(TutorServiceAppointment tutorServiceAppointment) {
		Calendar calendar = new Calendar.Builder().build();
		calendar.setTime(tutorServiceAppointment.getStart());
		Date startDate=calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, (int) tutorServiceAppointment.getDuration());
		return new Period(startDate,calendar.getTime());
	}

	private List<TutorServiceAppointment> getAllInCommingAppointmentsByTutor(int tutorId) {
		return ((AppointmentRepository)repository).getAllInCommingAppointmentsByTutor(tutorId);
	}
}
