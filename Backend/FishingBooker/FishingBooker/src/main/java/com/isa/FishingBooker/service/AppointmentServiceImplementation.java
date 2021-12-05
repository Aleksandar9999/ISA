package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.TutorServiceRepository;

@Service
public class AppointmentServiceImplementation extends CustomServiceAbstract<Appointment> implements AppointmentService {
	
	@Autowired
	private TutorServiceRepository tutorServiceRepository;
		
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
		TutorService tutorService=tutorServiceRepository.getById(app.getTutorService().getId());
		app.setPrice(tutorService.calculatePrice((int) app.getDuration()));
		super.addNew(app);
	}

}
