package com.isa.FishingBooker.service;


import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.BoatAppointmentRevision;
import com.isa.FishingBooker.model.ResortAppointmentRevision;
import com.isa.FishingBooker.model.Revision;
import com.isa.FishingBooker.model.TutorServiceAppointmentRevision;

public interface RevisionService extends Service<Revision> {
	
	public int getBoatAppointmentRevisionsRate(Integer id);
	public int getResortAppointmentRevisionsRate(Integer id);
	public int getTutorServiceAppointmentRevisionsRate(Integer id);
	public String makeTutorServiceRevision(TutorServiceAppointmentRevision revision);
	public String makeBoatRevision(BoatAppointmentRevision revision);
	public String makeResortRevision(ResortAppointmentRevision revision);
}
