package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Revision;

public interface RevisionService extends Service<Revision> {
	
	public int getBoatAppointmentRevisionsRate(Integer id);
	public int getResortAppointmentRevisionsRate(Integer id);
	public int getTutorServiceAppointmentRevisionsRate(Integer id);
}
