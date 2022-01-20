package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.TutorServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TutorServicesServiceImpl extends CustomServiceAbstract<TutorService> implements TutorServicesService {

	@Autowired
	private UsersService usersService;

	@Autowired 
	private EmailService emailService;
	
	@Override
	public void addNew(TutorService item) {
		item.setStatus(Status.CONFIRMED);
		super.addNew(item);
	}

	@Override
	public void delete(int id) {
		TutorService service = this.getById(id);
		service.setStatus(Status.DELETED);
		this.update(service);
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

	@Override
	public void addNewStandardPeriod(int idservice, Period period) {
		TutorService tutorService = this.getById(idservice);
		tutorService.addStandardPeriod(period);
		this.update(tutorService);
	}

	@Override
	public List<Period> getAllAvailablePeriodsByTutor(int idtutor) {
		Tutor tutor = (Tutor) usersService.getById(idtutor);
		return tutor.getServices().stream().map(TutorService::getStandardPeriods).flatMap(Set::stream)
				.collect(Collectors.toList());
	}
	@Override
	public List<DiscountOffer> getAllDiscountOffers(int tutorId){
		List<TutorService> tutorServices=this.getAllValidByTutor(tutorId);
		return tutorServices.stream().map(TutorService::getDisconutOffers).flatMap(Set::stream).collect(Collectors.toList());
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
		TutorService tutorService=this.getById(idservice);
		tutorService.addDiscountOffer(offer);
		this.update(tutorService);
		this.notifySubscribers(tutorService);
	}
	@Async
	private void notifySubscribers(TutorService tutorService) {
		tutorService.getSubscribers().forEach(subscirber-> emailService.sendDiscountNotificationEmail(subscirber, tutorService));
	}
}
