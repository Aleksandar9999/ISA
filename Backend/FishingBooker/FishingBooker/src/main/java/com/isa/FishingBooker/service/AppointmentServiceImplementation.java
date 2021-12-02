package com.isa.FishingBooker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

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

	@Override
	public List<Appoinment> getPendingApointments(String email) {
		List<Appoinment> appointments = new ArrayList<Appoinment>();
		Date thisMoment = new Date(System.currentTimeMillis());
		for(BoatAppointment a : repository.getAllBoatAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(ResortAppointment a : repository.getAllResortAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(TutorServiceAppointment a : repository.getAllTutorServiceAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		return appointments;
	}

}
