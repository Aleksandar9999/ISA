package com.isa.FishingBooker.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment ,Integer> {
	
	@Query(value="select start, duration, max_person, additional_services, price, address_id, resort_id, user_id from appoinment a where a.dtype=ResortAppointment", nativeQuery=true)
	public List<ResortAppointment> getAllResortAppoints();
	
	@Query(value="select start, duration, max_person, additional_services, price, address_id, boat_id, user_id from appoinment a where a.dtype=BoatAppointment", nativeQuery=true)
	public List<BoatAppointment> getAllBoatAppoints();
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment")
	public List<TutorServiceAppointment> getAllTutorServiceAppointments();
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1")
	public List<TutorServiceAppointment> getAllAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1 and a.start > CURRENT_DATE")
	public List<TutorServiceAppointment> getAllInCommingAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1")
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByUser(int tutorId);
	
	
	@Query(value="select dtype, id,start + interval '1' day * duration as endDate  , additional_services, duration, ts.max_person, price, start, ts.address_id, user_id, boat_id, resort_id, ts.tutor_id,ts.tutor_service_id, "
			+ "    ts.name from Appointment a INNER JOIN tutor_service as ts on a.tutor_service_id=ts.tutor_service_id where a.dtype='TutorServiceAppointment' and ts.tutor_id=?1 and "
			+ "(a.start, a.start + interval '1' day * a.duration) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, Date start,Date end);
	//TODO: Da li prebaciti polje duration/startdate u polje Period, radi lakseg rada a i da bi podrzali vrijeme zavrsetka

	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Appointment a WHERE a.resort_id=?1",nativeQuery = true)
	public void deleteResortAppointments(int resortId);
	
}
