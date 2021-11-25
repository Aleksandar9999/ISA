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
