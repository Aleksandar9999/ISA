package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Revision;
import com.isa.FishingBooker.model.Status;
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
	public double getBoatAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<BoatAppointmentRevision> revisions = ((RevisionRepository) repository).getBoatAppointmentRevisionById(id);
		double result = 0;
		for(BoatAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		result = result/revisions.size();
		return result;
	}

	@Override
	public double getResortAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<ResortAppointmentRevision> revisions = ((RevisionRepository) repository).getResortAppointmentRevisionById(id);
		double result = 0;
		for(ResortAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		result = result/revisions.size();
		return result;
	}

	@Override
	public double getTutorServiceAppointmentRevisionsRate(Integer id) {
		// TODO Auto-generated method stub
		List<TutorServiceAppointmentRevision> revisions = ((RevisionRepository) repository).getTutorServiceAppointmentRevisionById(id);
		double result = 0;
		for(TutorServiceAppointmentRevision r : revisions) {
			result+=r.getRate();
		}
		result = result/revisions.size();
		return result;
	}	
	
}
