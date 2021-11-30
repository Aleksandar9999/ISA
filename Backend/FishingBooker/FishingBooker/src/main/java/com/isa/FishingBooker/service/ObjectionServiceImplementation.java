package com.isa.FishingBooker.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isa.FishingBooker.model.Objection;
import com.isa.FishingBooker.repository.ObjectionRepository;

@Service
public class ObjectionServiceImplementation implements ObjectionService {

	@Autowired
	private ObjectionRepository repository;
	
	@Override
	public void addNew(Objection item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Objection> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Objection getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Objection item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Objection addObjection(Objection o) {
		return repository.save(o);
	}

	

}
