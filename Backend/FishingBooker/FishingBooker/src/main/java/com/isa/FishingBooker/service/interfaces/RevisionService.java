package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.revision.BoatAppointmentRevision;
import com.isa.FishingBooker.model.revision.ResortAppointmentRevision;
import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.revision.TutorServiceAppointmentRevision;

public interface RevisionService extends Service<Revision> {
	public void updateRevisionStatus(int revisionId, Status revisionStatus, int adminId);
	public double getBoatAppointmentRevisionsRate(Integer id);
	public double getResortAppointmentRevisionsRate(Integer id);
	public double getTutorServiceAppointmentRevisionsRate(Integer id);
	public String makeTutorServiceRevision(TutorServiceAppointmentRevision revision);
	public String makeBoatRevision(BoatAppointmentRevision revision);
	public String makeResortRevision(ResortAppointmentRevision revision);
	public void makeTutorRevision(Revision revision);
}
