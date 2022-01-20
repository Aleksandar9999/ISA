package com.isa.FishingBooker.service;

import com.isa.FishingBooker.exceptions.RevisionCreatorException;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.model.revision.BoatAppointmentRevision;
import com.isa.FishingBooker.model.revision.ResortAppointmentRevision;
import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.revision.TutorServiceAppointmentRevision;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.RevisionRepository;
import com.isa.FishingBooker.repository.UserRepository;

@org.springframework.stereotype.Service
public class RevisionServiceImpl extends CustomServiceAbstract<Revision> implements RevisionService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UserRepository usersRepository;

	@Override
	public void addNew(Revision item) {
		item.setStatus(Status.PENDING);
		item.setCreator(usersRepository.getById(item.getCreator().getId()));
		super.addNew(item);
	}

	@Override
	public void makeTutorRevision(Revision revision) {
		validateRevisionCreator(revision.getCreator().getId(), revision.getRelatedId());
		this.addNew(revision);
	}

	private void validateRevisionCreator(int creatorId, int tutorId) {
		if (appointmentRepository.getAllByTutorAndUserBeforeCurrentDate(creatorId, tutorId).size() == 0) {
			throw new RevisionCreatorException();
		}
	}

	@Override
	public int getBoatAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<BoatAppointmentRevision> revisions = ((RevisionRepository) repository).getBoatAppointmentRevisionById(id);
		int result = 0;
		for (BoatAppointmentRevision r : revisions) {
			result += r.getRate();
		}
		if (revisions.size() != 0) {
			result = result / revisions.size();
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public int getResortAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<ResortAppointmentRevision> revisions = ((RevisionRepository) repository)
				.getResortAppointmentRevisionById(id);
		int result = 0;
		for (ResortAppointmentRevision r : revisions) {
			result += r.getRate();
		}
		if (revisions.size() != 0) {
			result = result / revisions.size();
		} else {
			result = 0;
		}

		return (int) result;
	}

	@Override
	public double getTutorServiceAppointmentRevisionsRate(Integer id) {
		List<TutorServiceAppointmentRevision> revisions = ((RevisionRepository) repository)
				.getTutorServiceAppointmentRevisionById(id);
		if (revisions.size() == 0)
			return 0;
		return revisions.stream().mapToDouble(TutorServiceAppointmentRevision::getRate).sum() / revisions.size();
	}

	@Override
	public String makeTutorServiceRevision(TutorServiceAppointmentRevision revision) {
		// TODO Auto-generated method stub
		super.addNew(revision);
		return null;
	}

	@Override
	public String makeBoatRevision(BoatAppointmentRevision revision) {
		// TODO Auto-generated method stub
		super.addNew(revision);
		return null;
	}

	@Override
	public String makeResortRevision(ResortAppointmentRevision revision) {
		// TODO Auto-generated method stub
		super.addNew(revision);
		return null;
	}

}
