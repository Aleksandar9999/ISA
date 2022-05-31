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
import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.ResortOwner;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.BoatRepository;
import com.isa.FishingBooker.repository.ResortRepository;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.ResortsService;
import com.isa.FishingBooker.service.interfaces.UsersService;
@Service
public class ResortsServiceImplementation extends CustomGenericService<Resort> implements ResortsService {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AppointmentService appointmentService;

	@Override
	public void addNew(Resort item) {
		item.setStatus(Status.CONFIRMED);
		item.setResortOwner((ResortOwner)usersService.getById(item.getResortOwnerId()));
		super.addNew(item);
		
	}

	@Override
	public List<Resort> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resort getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Resort item) {
		super.update(this.getById(item.getId()).updateInfo(item));
		
	}

	@Override
	public void delete(Integer id) {
		Resort resort = this.getById(id);
		resort.setStatus(Status.DELETED);
		this.updateInfo(resort);
		
	}

	@Override
	public void delete(int id) {
		Resort resort = this.getById(id);
		resort.setStatus(Status.DELETED);
		this.updateInfo(resort);
		
	}

	@Override
	public List<Resort> getAllValid() {
		return ((ResortRepository) repository).findAllValid();
	}

	@Override
	public List<Resort> getAllValidByResortOwner(int resortownerId) {
		return ((ResortRepository) repository).findAllValidByResortOwner(resortownerId);
	}

	@Override
	public void addPhoto(int idresort, Photo photo) {
		Resort resort = this.getById(idresort);
		resort.addPhoto(photo);
		this.update(resort);
		
	}

	@Override
	public void deletePhoto(int idresort, int idphoto) {
		Resort resort = this.getById(idresort);
		resort.deletePhoto(idphoto);
		this.update(resort);
		
	}

	@Transactional
	@Override
	public void addNewStandardPeriod(int idresort, Period newPeriod) {
		Resort resort = this.getById(idresort);
		resort.getResortAvailable().forEach(period->{
				period.overlap(newPeriod);		
		});
		resort.addAvailablePeriod(newPeriod);
		update(resort);
		
	}

	@Override
	public List<Period> getAllAvailablePeriodsByResort(int idresort) {
		Resort resort = this.getById(idresort);
		return resort.getResortAvailable().stream().collect(Collectors.toList());
	}

	@Override
	public void addNewCustomer(int idresort, User loggedinUser) {
		Resort resort = this.getById(idresort);
		resort.addNewCustomer(usersService.getById(loggedinUser.getId()));
		this.update(resort);
		
	}

	@Override
	public void removeCustomer(int idresort, User loggedinUser) {
		this.update(this.getById(idresort).removeCustomer(usersService.getById(loggedinUser.getId())));
		
	}

	@Override
	public void addNewDiscountOffer(int idresort, DiscountOffer offer) {
		Resort resort = this.getById(idresort);
		resort.addDiscountOffer(offer);
		this.update(resort);
		this.notifyCustomers(resort);
		
	}

	@Override
	public List<DiscountOffer> getAllDiscountOffers(int idResort) {
		Resort resort = this.getById(idResort);
		return resort.getDiscountOffers().stream().collect(Collectors.toList());
		
	}

	@Override
	public List<Resort> getAllResortsAvailablePeriods(Timestamp start, int duration) {
		List<Resort> retList = new ArrayList<Resort>();
		Period newPeriod = Period.createPeriod(start, duration);
		List<Resort> resorts = this.getAll().stream().collect(Collectors.toList());				
		for (Resort resort : resorts) {
			resort.getResortAvailable().forEach(period -> {
				try {
					period.periodBetweenPeriod(newPeriod);
				} catch (PeriodOverlapException e) {
					retList.add(resort);
				}
			});
		}
		return retList;
	}

	@Override
	public void updateInfo(Resort newInfo) {
		List<ResortAppointment> appointments=this.appointmentService.getAllPendingByResortId(newInfo.getId());
		if(!appointments.isEmpty()) throw new PendingAppointmentExistException();
		this.update(newInfo);
		
		
	}
	
	@Async
	private void notifyCustomers(Resort resort) {
		resort.getCustomers()
				.forEach(customer -> emailService.sendDiscountNotificationEmailResorts(customer, resort));
	}

	@Override
	public Resort getResortById(int id) {
		return (Resort) ((ResortRepository) repository).findResortById(id);
	}
	

}
