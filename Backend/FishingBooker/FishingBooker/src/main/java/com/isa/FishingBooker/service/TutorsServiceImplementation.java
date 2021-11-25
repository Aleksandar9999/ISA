package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.repository.TutorServiceRepository;
@Service
public class TutorsServiceImplementation implements TutorsService {
	
	@Autowired
	private TutorServiceRepository repository;
	
	@Override
	public void addNew(Tutor item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tutor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tutor getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Tutor item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
