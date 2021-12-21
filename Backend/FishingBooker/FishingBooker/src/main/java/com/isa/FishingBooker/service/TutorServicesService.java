package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Set;

import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.TutorService;

public interface TutorServicesService extends Service<TutorService>{
	public void delete(int id);
	public List<TutorService> getAllValid();
	public List<TutorService> getAllValidByTutor(int tutorId);
	public void addPhoto(int idservice, Photo photo);
	public void deletePhoto(int idservice, int idphoto);
	public void addNewStandardPeriod(int idservice, Period period);
	public List<Period> getAllAvailablePeriodsByTutor(int idtutor);
}
