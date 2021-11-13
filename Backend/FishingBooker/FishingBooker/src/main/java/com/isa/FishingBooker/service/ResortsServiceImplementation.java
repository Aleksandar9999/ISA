package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dao.ResortDAO;
import com.isa.FishingBooker.model.Resort;
@Service
public class ResortsServiceImplementation implements ResortsService {

	@Autowired
	private ResortDAO dao;
	
	@Override
	public void addNew(Resort item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resort> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resort getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Resort item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}


}
