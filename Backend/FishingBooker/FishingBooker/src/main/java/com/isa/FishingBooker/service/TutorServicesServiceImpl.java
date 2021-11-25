package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.isa.FishingBooker.dao.TutorServicesDAO;
import com.isa.FishingBooker.model.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TutorServicesServiceImpl implements TutorServicesService{

    @Autowired
    private TutorServicesDAO tutorServicesDAO;
    @Override
    public void addNew(TutorService item) {
        tutorServicesDAO.addNew(item);
    }

    @Override
    public List<TutorService> getAll() {
        return tutorServicesDAO.getAll();
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
