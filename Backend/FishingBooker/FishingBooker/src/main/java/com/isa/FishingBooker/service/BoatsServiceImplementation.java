package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.exceptions.PendingAppointmentExistException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.BoatRepository;
import com.isa.FishingBooker.repository.TutorServiceRepository;
import com.isa.FishingBooker.repository.UserRepository;
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
		item.setBoatOwner((BoatOwner)usersService.getById(item.getBoatOwnerId()));
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
		Boat boat = this.getById(idboat);
		boat.addPhoto(photo);
		this.update(boat);
		
	}

	@Override
	public void deletePhoto(int idboat, int idphoto) {
		Boat boat = this.getById(idboat);
		boat.deletePhoto(idphoto);
		this.update(boat);
		
	}
	
	@Transactional
	@Override
	public void addNewStandardPeriod(int idBoat, Period newPeriod) {
		Boat boat= getById(idBoat);
		boat.getAvailable().forEach(period->{
				period.overlap(newPeriod);		
		});
		boat.addAvailablePeriod(newPeriod);
		update(boat);
		
	}

	@Override
	public List<Period> getAllAvailablePeriodsByBoat(int idBoat) {
		Boat boat =  getById(idBoat);
		return boat.getAvailable().stream().collect(Collectors.toList());
	}

	@Override
	public void addNewCustomer(int boatId, User loggedinUser) {
		Boat boat=this.getById(boatId);
		boat.addNewCustomer(usersService.getById(loggedinUser.getId()));
		this.update(boat);
		
	}

	@Override
	public void removeCustomer(int boatId, User loggedinUser) {
		this.update(this.getById(boatId).removeCustomer(usersService.getById(loggedinUser.getId())));
		
	}

	@Override
	public void addNewDiscountOffer(int idboat, DiscountOffer offer) {
		Boat boat = this.getById(idboat);
		boat.addDiscountOffer(offer);
		this.update(boat);
		this.notifyCustomers(boat);
		
	}

	@Override
	public List<DiscountOffer> getAllDiscountOffers(int idBoat) {
		Boat boat =  getById(idBoat);
		return boat.getDisconutOffers().stream().collect(Collectors.toList());
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
	public double avgRateBoat (int boatownerId) {
		double rateSum = 0;
		double avgRate=0;
		int brojac=0;
		List<Boat> boats =((BoatRepository) repository).findAllValidByBoatOwner(boatownerId);
		for(Boat boat : boats) {
			rateSum += boat.getRate();
			brojac++;
		}
		avgRate = rateSum/brojac;
		return avgRate;
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
	
	@Override
	public Boat getBoatById(int id) {
		return (Boat) ((BoatRepository) repository).findBoatById(id);
	}

	
	
	


}
