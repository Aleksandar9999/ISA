package com.isa.FishingBooker.service;

import com.isa.FishingBooker.exceptions.PendingAppointmentExistException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.model.*;
import com.isa.FishingBooker.repository.TutorServiceRepository;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;
import com.isa.FishingBooker.service.interfaces.UsersService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TutorServicesServiceImpl extends CustomGenericService<TutorService> implements TutorServicesService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AppointmentService appointmentService;

	@Override
	public void addNew(TutorService item) {
		item.setStatus(Status.CONFIRMED);
		item.setTutor(usersService.getTutorById(item.getTutorId()));
		super.addNew(item);
	}

	@Override
	public void delete(int id) {
		TutorService service = this.getById(id);
		service.setStatus(Status.DELETED);
		this.updateInfo(service);
	}
	
	@Override
	public void update(TutorService item) {
		super.update(this.getById(item.getId()).updateInfo(item));
	}

	@Override
	public List<TutorService> getAllValid() {
		return ((TutorServiceRepository) repository).findAllValid();
	}

	@Override
	public List<TutorService> getAllValidByTutor(int tutorId) {
		return ((TutorServiceRepository) repository).findAllValidByTutor(tutorId);
	}

	@Override
	public void addPhoto(int idservice, Photo photo) {
		TutorService tutorService = this.getById(idservice);
		tutorService.addPhoto(photo);
		this.update(tutorService);
	}

	@Override
	public void deletePhoto(int idservice, int idphoto) {
		TutorService tutorService = this.getById(idservice);
		tutorService.deletePhoto(idphoto);
		this.update(tutorService);
	}

	@Transactional
	@Override
	public void addNewStandardPeriod(int idTutor, Period newPeriod) {
		Tutor tutor=usersService.getTutorById(idTutor);
		tutor.getAvailable().forEach(period->{
				period.overlap(newPeriod);		
		});
		tutor.addAvailablePeriod(newPeriod);
		usersService.update(tutor);
	}

	@Override
	public List<Period> getAllAvailablePeriodsByTutor(int idtutor) {
		Tutor tutor = (Tutor) usersService.getById(idtutor);
		return tutor.getAvailable().stream().collect(Collectors.toList());
	}

	@Override
	public List<DiscountOffer> getAllDiscountOffers(int tutorId) {
		List<TutorService> tutorServices = this.getAllValidByTutor(tutorId);
		return tutorServices.stream().map(TutorService::getDisconutOffers).flatMap(Set::stream)
				.collect(Collectors.toList());
	}

	@Override
	public void addNewSubscriber(int serviceId, User loggedinUser) {
		this.update(this.getById(serviceId).addNewSubscriber(loggedinUser));
	}

	@Override
	public void removeSubscriber(int serviceId, User loggedinUser) {
		this.update(this.getById(serviceId).removeSubscriber(loggedinUser));
	}

	@Override
	public void addNewDiscountOffer(int idservice, DiscountOffer offer) {
		TutorService tutorService = this.getById(idservice);
		tutorService.addDiscountOffer(offer);
		this.update(tutorService);
		this.notifySubscribers(tutorService);
	}

	@Override
	public List<TutorService> getAllTutorServicesAvailablePeriods(Timestamp start, int duration, int maxPerson) {
		List<TutorService> retList = new ArrayList<TutorService>();
		Period newPeriod = Period.createPeriod(start, duration);
		List<TutorService> services = this.getAll().stream().filter(service -> service.getMaxPerson() > maxPerson)
				.collect(Collectors.toList());
		for (TutorService tutorService : services) {
			tutorService.getTutor().getAvailable().forEach(period -> {
				try {
					period.periodBetweenPeriod(newPeriod);
				} catch (PeriodOverlapException e) {
					retList.add(tutorService);
				}
			});
		}
		return retList;
	}
	@Override
	public void updateInfo(TutorService newInfo) {
		List<TutorServiceAppointment> appointments=this.appointmentService.getAllPendingByTutorServiceId(newInfo.getId());
		if(!appointments.isEmpty()) throw new PendingAppointmentExistException();
		this.update(newInfo);
	}
	
	@Async
	private void notifySubscribers(TutorService tutorService) {
		tutorService.getSubscribers()
				.forEach(subscirber -> emailService.sendDiscountNotificationEmail(subscirber, tutorService));
	}
}
