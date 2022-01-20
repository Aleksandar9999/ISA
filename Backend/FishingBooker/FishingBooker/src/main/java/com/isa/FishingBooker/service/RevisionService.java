package com.isa.FishingBooker.service;


import com.isa.FishingBooker.model.revision.BoatAppointmentRevision;
import com.isa.FishingBooker.model.revision.ResortAppointmentRevision;
import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.revision.TutorServiceAppointmentRevision;

public interface RevisionService extends Service<Revision> {
	
	public int getBoatAppointmentRevisionsRate(Integer id);
	public int getResortAppointmentRevisionsRate(Integer id);
	public double getTutorServiceAppointmentRevisionsRate(Integer id);
	public String makeTutorServiceRevision(TutorServiceAppointmentRevision revision);
	public String makeBoatRevision(BoatAppointmentRevision revision);
	public String makeResortRevision(ResortAppointmentRevision revision);
	public void makeTutorRevision(Revision revision);
}
