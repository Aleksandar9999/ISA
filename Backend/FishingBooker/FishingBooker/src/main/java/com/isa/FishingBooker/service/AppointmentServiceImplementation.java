package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Appoinment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.repository.AppointmentRepository;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
	
	@Autowired
	private AppointmentRepository repository;
	
	@Override
	public void addNew(Appoinment item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appoinment> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Appoinment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Appoinment item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ResortAppointment> getResortApointments(){
		return repository.getAllResortAppoints();
	}
	
	public List<BoatAppointment> getBoatApointments(){
		return repository.getAllBoatAppoints();
	}
	
	public List<TutorServiceAppointment> getTutorServiceApointments(){
		return repository.getAllTutorServiceAppoints();
	}

}
