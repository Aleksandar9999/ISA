package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.BoatRepository;
import com.isa.FishingBooker.service.interfaces.BoatsService;

@Service
public class BoatsServiceImplementation implements BoatsService {

	@Override
	public void addNew(Boat item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Boat> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boat getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Boat item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Boat> getAllValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boat> getAllValidByBoatOwner(int boatownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPhoto(int idboat, Photo photo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePhoto(int idboat, int idphoto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewStandardPeriod(int idBoatOwner, Period period) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Period> getAllAvailablePeriodsByBoatOwner(int idboatowner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewCustomer(int boatId, User loggedinUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCustomer(int boatId, User loggedinUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewDiscountOffer(int idboat, DiscountOffer offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DiscountOffer> getAllDiscountOffers(int boatownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boat> getAllBoatsAvailablePeriods(Timestamp start, int duration, int maxPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInfo(Boat newInfo) {
		// TODO Auto-generated method stub
		
	}
	


}
