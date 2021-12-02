package com.isa.FishingBooker.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.repository.PeriodsRepository;

@Service
public class PeriodServiceImplementation implements PeriodService{
	
	@Autowired
	private PeriodsRepository repository;
	@Override
	public void addNew(Period item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Period> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Period getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Period item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
