package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.TutorServiceAppointmentRevision;

import java.util.List;

import com.isa.FishingBooker.model.BoatAppointmentRevision;
import com.isa.FishingBooker.model.ResortAppointmentRevision;
import com.isa.FishingBooker.repository.RevisionRepository;

@org.springframework.stereotype.Service
public class RevisionServiceImpl extends CustomServiceAbstract<Revision> implements RevisionService {	
	
	@Override
	public void addNew(Revision item) {
		item.setStatus(Status.PENDING);
		super.addNew(item);
	}

	@Override
	public int getBoatAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<BoatAppointmentRevision> revisions = ((RevisionRepository) repository).getBoatAppointmentRevisionById(id);
		int result = 0;
		for(BoatAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		if(revisions.size()!=0) {
			result = result/revisions.size();			
		} else {
			result= 0;
		}
		return result;
	}

	@Override
	public int getResortAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<ResortAppointmentRevision> revisions = ((RevisionRepository) repository).getResortAppointmentRevisionById(id);
		int result = 0;
		for(ResortAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		if(revisions.size()!=0) {
			result = result/revisions.size();
		} else {
			result= 0;
		}
			
		return (int) result;
	}

	@Override
	public int getTutorServiceAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<TutorServiceAppointmentRevision> revisions = ((RevisionRepository) repository).getTutorServiceAppointmentRevisionById(id);
		int result = 0;
		for(TutorServiceAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		if(revisions.size()!=0) {
			result = result/revisions.size();
		} else {
			result= 0;
		}
		return (int) result;
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
