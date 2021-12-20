package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.repository.TutorServiceRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TutorServicesServiceImpl extends CustomServiceAbstract<TutorService> implements TutorServicesService {
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
}
