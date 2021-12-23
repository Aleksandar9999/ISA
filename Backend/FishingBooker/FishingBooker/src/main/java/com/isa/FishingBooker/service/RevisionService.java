package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Revision;

public interface RevisionService extends Service<Revision> {
	
	public double getBoatAppointmentRevisionsRate(Integer id);
	public double getResortAppointmentRevisionsRate(Integer id);
	public double getTutorServiceAppointmentRevisionsRate(Integer id);
}
