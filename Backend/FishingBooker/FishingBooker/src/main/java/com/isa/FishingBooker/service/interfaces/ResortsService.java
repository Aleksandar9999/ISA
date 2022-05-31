package com.isa.FishingBooker.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.User;

public interface ResortsService extends Service<Resort> {
	public void delete(int id);
	public List<Resort> getAllValid();
	public List<Resort> getAllValidByResortOwner(int resortownerId);
	public void addPhoto(int idresort, Photo photo);
	public void deletePhoto(int idresort, int idphoto);
	public void addNewStandardPeriod(int idresort, Period period);
	public List<Period> getAllAvailablePeriodsByResort(int idresort);
	public void addNewCustomer(int idresort, User loggedinUser);
	public void removeCustomer(int idresort, User loggedinUser);
	public void addNewDiscountOffer(int idresort, DiscountOffer offer);
	public List<DiscountOffer> getAllDiscountOffers(int resortownerId);
	public List<Resort> getAllResortsAvailablePeriods(Timestamp start, int duration);
	public void updateInfo(Resort newInfo);
	public Resort getResortById(int id);
}
