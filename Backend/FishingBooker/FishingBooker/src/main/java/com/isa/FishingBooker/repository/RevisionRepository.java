package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.TutorServiceAppointmentRevision;
import com.isa.FishingBooker.model.BoatAppointmentRevision;
import com.isa.FishingBooker.model.ResortAppointmentRevision;
@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer>{
	
	@Query("select r from Revision r where TYPE(r)=BoatAppointmentRevision and r.boatAppointment.boat.id=?1")
	public List<BoatAppointmentRevision> getBoatAppointmentRevisionById(Integer id);
	
	@Query("select r from Revision r where TYPE(r)=ResortAppointmentRevision and r.resortAppointment.resort.id=?1")
	public List<ResortAppointmentRevision> getResortAppointmentRevisionById(Integer id);
	
	@Query("select r from Revision r where TYPE(r)=TutorServiceAppointmentRevision and r.tutorServiceAppointment.tutorService.id=?1")
	public List<TutorServiceAppointmentRevision> getTutorServiceAppointmentRevisionById(Integer id);
}
