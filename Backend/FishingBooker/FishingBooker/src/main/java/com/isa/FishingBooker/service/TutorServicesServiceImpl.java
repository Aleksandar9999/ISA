package com.isa.FishingBooker.service;

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
		TutorService service =this.getById(item.getId());
		service.updateInfo(item);
		super.update(service);
	}

	@Override
	public List<TutorService> getAllValid() {
		return ((TutorServiceRepository) repository).findAllValid();
	}

	@Override
	public List<TutorService> getAllValidByTutor(int tutorId) {
		return ((TutorServiceRepository) repository).findAllValidByTutor(tutorId);
	}
}
