package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.repository.TutorServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TutorServicesServiceImpl implements TutorServicesService{

    @Autowired
    private TutorServiceRepository repository;
    
    @Override
    @Transactional
    public void addNew(TutorService item) {
        repository.save(item);
    }

    @Override
    @Transactional
    public List<TutorService> getAll() {
        return repository.findAll();
    }
    @Override
    public TutorService getById(int id) {
    	return repository.findById(id).orElse(null);
    }

    @Override
    public void update(TutorService item) {
       repository.save(item);
        
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public TutorService getWithPhotos(int id) {
		return repository.findTutorServiceWithPhotos(id);
	}

	@Override
	public TutorService getWithDiscountOffers(int id) {
		return repository.findTutorServiceWithDiscountOffers(id);
	}

}
