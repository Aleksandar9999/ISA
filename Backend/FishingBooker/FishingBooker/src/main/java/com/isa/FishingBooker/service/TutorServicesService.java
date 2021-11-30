package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Optional;

import com.isa.FishingBooker.model.TutorService;

public interface TutorServicesService extends Service<TutorService>{
    public TutorService getById(int id);
    public TutorService getWithPhotos(int id);
    public TutorService getWithDiscountOffers(int id);
   // public List<TutorService> findByTutorIdAll(int tutorId);
}
