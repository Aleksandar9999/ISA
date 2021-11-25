package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import com.isa.FishingBooker.dao.TutorServicesDAO;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.repository.TutorServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TutorServicesServiceImpl implements TutorServicesService{

    @Autowired
    private TutorServiceRepository repository;
    
    @Override
    public void addNew(TutorService item) {
        //tutorServicesDAO.addNew(item);
    }

    @Override
    @Transactional
    public List<TutorService> getAll() {
        return repository.findAll();
    }

    @Override
    public TutorService getById(String id) {
        return tutorServicesDAO.getById(id);
    }

    @Override
    public void update(TutorService item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }
    
}
