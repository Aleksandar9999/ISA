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
        tutorService.addPhoto(new Photo("url", "naslov"));
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
        // TODO Auto-generated method stub
        return null;
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
