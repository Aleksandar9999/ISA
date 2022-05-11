package com.isa.FishingBooker.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment ,Integer> {
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.id=?1")
	public TutorServiceAppointment getResortAppointmentById(Integer id);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.id=?1")
	public ResortAppointment getTutorServiceAppointmentById(Integer id);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.id=?1")
	public BoatAppointment getBoatAppointmentById(Integer id);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment")
	public List<ResortAppointment> getAllResortAppoints();
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment")
	public List<BoatAppointment> getAllBoatAppoints();
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment")
	public List<TutorServiceAppointment> getAllTutorServiceAppointments();
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1")
	public List<TutorServiceAppointment> getAllAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1 and a.period.startDate > CURRENT_DATE")
	public List<TutorServiceAppointment> getAllInCommingAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1")
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByUser(int userid);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1 and a.tutorService.tutor.id=?2 and a.period.startDate < CURRENT_DATE")
	public List<TutorServiceAppointment> getAllByTutorAndUserBeforeCurrentDate(int userid, int tutorid);
	
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services, ts.max_person, price, ps.start_date, ts.address_id, user_id, boat_id, resort_id, ts.tutor_id,ts.tutor_service_id, "
			+ "    ts.name from Appointment a INNER JOIN tutor_service as ts on a.tutor_service_id=ts.tutor_service_id "
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='TutorServiceAppointment' and ts.tutor_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, Date start,Date end);
	
	@Query(value="select * from Appointment where "
			+ "(period.start_date, period.end_date) OVERLAPS (CAST(?1 as date), CAST(?2 as date))",nativeQuery = true)
	public List<Appointment> getAllInPeriod(Date start,Date end);
	@Query("select a from Appointment a where a.tutorService.id=?1 and a.status='PENDING'")
	public List<Appointment> getAllPendingByTutorServiceId(int id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Appointment a WHERE a.resort_id=?1",nativeQuery = true)
	public void deleteResortAppointments(int resortId);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Appointment save(Appointment a);
}
