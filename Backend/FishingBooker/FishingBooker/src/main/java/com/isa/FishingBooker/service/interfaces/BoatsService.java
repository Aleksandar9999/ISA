package com.isa.FishingBooker.service.interfaces;
import java.sql.Timestamp;
import java.util.List;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;

public interface BoatsService extends Service<Boat> {
	public void delete(int id);
	public List<Boat> getAllValid();
	public List<Boat> getAllValidByBoatOwner(int boatownerId);
	public void addPhoto(int idboat, Photo photo);
	public void deletePhoto(int idboat, int idphoto);
	public void addNewStandardPeriod(int idBoat, Period period);
	public List<Period> getAllAvailablePeriodsByBoat(int idboat);
	public void addNewCustomer(int boatId, User loggedinUser);
	public void removeCustomer(int boatId, User loggedinUser);
	public void addNewDiscountOffer(int idboat, DiscountOffer offer);
	public List<DiscountOffer> getAllDiscountOffers(int boatownerId);
	public List<Boat> getAllBoatsAvailablePeriods(Timestamp start, int duration, int maxPerson);
	public void updateInfo(Boat newInfo);
	public Boat getBoatById(int id);
}
