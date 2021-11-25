package com.isa.FishingBooker.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.BoatsService;

@Repository
public class BoatsDAOImpl implements BoatsDAO{

	 private static List<Boat> boats=new ArrayList<>();
	@Override
	public void addNew(Boat item) {
		// TODO Auto-generated method stub
		boats.add(item);
	}

	@Override
	public List<Boat> getAll() {

		return boats;
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