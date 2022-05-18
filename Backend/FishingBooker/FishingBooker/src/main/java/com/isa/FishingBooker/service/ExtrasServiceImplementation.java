package com.isa.FishingBooker.service;

import java.util.List;

import com.isa.FishingBooker.model.Extras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.repository.ExtrasRepository;
import com.isa.FishingBooker.service.interfaces.ExtrasService;

@Service
public class ExtrasServiceImplementation implements ExtrasService {
	
	@Autowired
	private ExtrasRepository repository;
	
	@Override
	public void addNew(Extras item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Extras> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Extras getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Extras item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
