package com.isa.FishingBooker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dao.BoatsDAO;
import com.isa.FishingBooker.model.Boat;

@Service
public class BoatsServiceImplementation implements BoatsService {
	
	@Autowired
	private BoatsDAO dao;
	
	@Override
	public void addNew(Boat item) {		
		 dao.addNew(item);
	}

	@Override
	public List<Boat> getAll() {
		return dao.getAll();
	}

	@Override
	public Boat getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Boat item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
