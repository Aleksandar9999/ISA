package com.isa.FishingBooker.dao;

import java.util.ArrayList;
import java.util.List;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.TutorService;

import org.springframework.stereotype.Repository;
@Repository
public class TutorServicesDaoInMemory implements TutorServicesDAO{

    private static List<TutorService> tutorServices=new ArrayList<>();
    public TutorServicesDaoInMemory() {
        TutorService tutorService=new TutorService("Pecanje", "Kratak opis pecanja", 3, "Pravila ponasanje:", "Oprema koju dobijate za pecanje ukoliko ne poneste svoju.", 3, new Address()); 
        tutorService.addPhoto(new Photo("http://www.radiobijelopolje.me/images/2019-lifestyle1/25-03-druenje.jpg", "TestImage"));
        tutorServices.add(tutorService);
     }

    @Override
    public void addNew(TutorService item) {
        tutorServices.add(item);
    }

    @Override
    public List<TutorService> getAll() {
       return tutorServices;
    }

    @Override
    public TutorService getById(String id) {
        return tutorServices.get(0);
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
