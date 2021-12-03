package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Optional;

import com.isa.FishingBooker.model.TutorService;

public interface TutorServicesService extends Service<TutorService>{
	public void delete(int id);
	public List<TutorService> getAllValid();
}
