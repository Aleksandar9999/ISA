package com.isa.FishingBooker.service;

import com.isa.FishingBooker.exceptions.RevisionCreatorException;
import com.isa.FishingBooker.model.Admin;
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
import com.isa.FishingBooker.service.interfaces.RevisionService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@org.springframework.stereotype.Service
public class RevisionServiceImpl extends CustomGenericService<Revision> implements RevisionService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private EmailService emailService;
	@Override
	public void addNew(Revision item) {
		item.setStatus(Status.PENDING);
		item.setCreator(usersService.getById(item.getCreator().getId()));
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
	public void updateRevisionStatus(int revisionId, Status revisionStatus, int adminId) {
		Revision dbRevision=this.getById(revisionId);
		User byId = usersService.getById(adminId);
		dbRevision.setAdminResponded((Admin) byId);
		dbRevision.setStatus(revisionStatus);
		super.update(dbRevision);
		if(dbRevision.getStatus().equals(Status.ADMIN_CONFIRMED))
			emailService.sendCustomEmail(dbRevision.getOwnerEmail(),"New revision approved", "New revision approved:\n"+dbRevision.getComment()+"\nFishing booker team");

	}

	@Override
	public double getBoatAppointmentRevisionsRate(Integer id) {
		List<BoatAppointmentRevision> revisions = ((RevisionRepository) repository)
				.getBoatAppointmentRevisionById(id);
		if (revisions.size() == 0)
			return 0;
		return revisions.stream().mapToDouble(BoatAppointmentRevision::getRate).sum() / revisions.size();
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
		super.addNew(revision);
		return null;
	}

	@Override
	public String makeBoatRevision(BoatAppointmentRevision revision) {
		super.addNew(revision);
		return null;
	}

	@Override
	public String makeResortRevision(ResortAppointmentRevision revision) {
		super.addNew(revision);
		return null;
	}

}
