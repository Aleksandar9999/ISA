package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.exceptions.PendingAppointmentExistException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.BoatRepository;
import com.isa.FishingBooker.repository.TutorServiceRepository;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@Service
public class BoatsServiceImplementation extends CustomGenericService<Boat> implements BoatsService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Override
	public void addNew(Boat item) {
		item.setStatus(Status.CONFIRMED);
		item.setBoatOwner(usersService.getBoatOwnerById(item.getBoatOwnerId()));
		super.addNew(item);
		
	}



	@Override
	public void update(Boat item) {
		super.update(this.getById(item.getId()).updateInfo(item));
		
	}



	@Override
	public void delete(int id) {
		Boat boat = this.getById(id);
		boat.setStatus(Status.DELETED);
		this.updateInfo(boat);
	}

	@Override
	public List<Boat> getAllValid() {
		return ((BoatRepository) repository).findAllValid();
	}

	@Override
	public List<Boat> getAllValidByBoatOwner(int boatownerId) {
		return ((BoatRepository) repository).findAllValidByBoatOwner(boatownerId);
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
		this.update(this.getById(boatId).addNewCustomer(loggedinUser));
		
	}

	@Override
	public void removeCustomer(int boatId, User loggedinUser) {
		this.update(this.getById(boatId).removeCustomer(loggedinUser));
		
	}

	@Override
	public void addNewDiscountOffer(int idboat, DiscountOffer offer) {
		Boat boat = this.getById(idboat);
		boat.addDiscountOffer(offer);
		this.update(boat);
		this.notifyCustomers(boat);
		
	}

	@Override
	public List<DiscountOffer> getAllDiscountOffers(int boatownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boat> getAllBoatsAvailablePeriods(Timestamp start, int duration, int maxPerson) {
		List<Boat> retList = new ArrayList<Boat>();
		Period newPeriod = Period.createPeriod(start, duration);
		List<Boat> boats = this.getAll().stream().filter(boat -> boat.getMaxPerson() > maxPerson)
				.collect(Collectors.toList());
		for (Boat boat : boats) {
			boat.getAvailable().forEach(period -> {
				try {
					period.periodBetweenPeriod(newPeriod);
				} catch (PeriodOverlapException e) {
					retList.add(boat);
				}
			});
		}
		return retList;
	}

	@Override
	public void updateInfo(Boat newInfo) {
		List<BoatAppointment> appointments=this.appointmentService.getAllPendingByBoatId(newInfo.getId());
		if(!appointments.isEmpty()) throw new PendingAppointmentExistException();
		this.update(newInfo);
		
	}
	
	@Async
	private void notifyCustomers(Boat boat) {
		boat.getCustomers()
				.forEach(customer -> emailService.sendDiscountNotificationEmailBoats(customer, boat));
	}

	
	
	


}
