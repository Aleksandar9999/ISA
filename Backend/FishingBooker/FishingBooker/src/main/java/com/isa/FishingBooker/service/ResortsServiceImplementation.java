package com.isa.FishingBooker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.repository.ResortRepository;
@Service
public class ResortsServiceImplementation implements ResortsService {

	@Autowired
	private ResortRepository repository;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Override
	public void addNew(Resort item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Resort> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Resort getById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void update(Resort item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		appointmentService.deleteResortAppointments(id);
		repository.deleteById(id);
	}
}
