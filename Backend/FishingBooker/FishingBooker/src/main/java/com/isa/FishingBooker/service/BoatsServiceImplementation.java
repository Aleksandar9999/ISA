package com.isa.FishingBooker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.repository.BoatRepository;

@Service
public class BoatsServiceImplementation implements BoatsService {
	
	@Autowired
	private BoatRepository repository;
	
	@Override
	public void addNew(Boat item) {		
		 //dao.addNew(item);
	}

	@Override
	@Transactional
	public List<Boat> getAll() {
		return repository.findAll();
	}

	@Override
	public Boat getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void update(Boat item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
