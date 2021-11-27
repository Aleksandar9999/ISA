package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Appoinment;
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
	
	public List<Appoinment> getResortApointments(){
		return repository.getAllResortAppoints();
	}
	
	public List<Appoinment> getBoatApointments(){
		return repository.getAllBoatAppoints();
	}
	
	public List<Appoinment> getTutorServiceApointments(){
		return repository.getAllTutorServiceAppoints();
	}

}
