package com.isa.FishingBooker.service.interfaces;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;

public interface TutorServicesService extends Service<TutorService>{
	public void delete(int id);
	public List<TutorService> getAllValid();
	public List<TutorService> getAllValidByTutor(int tutorId);
	public void addPhoto(int idservice, Photo photo);
	public void deletePhoto(int idservice, int idphoto);
	public void addNewStandardPeriod(int idTutor, Period period);
	public List<Period> getAllAvailablePeriodsByTutor(int idtutor);
	public void addNewSubscriber(int serviceId, User loggedinUser);
	public void removeSubscriber(int serviceId, User loggedinUser);
	public void addNewDiscountOffer(int idservice, DiscountOffer offer);
	public List<DiscountOffer> getAllDiscountOffers(int tutorId);
	public List<TutorService> getAllTutorServicesAvailablePeriods(Timestamp start, int duration, int maxPerson);
	public void updateInfo(TutorService newInfo);
}
