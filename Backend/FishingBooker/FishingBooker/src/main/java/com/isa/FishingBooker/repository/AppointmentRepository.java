package com.isa.FishingBooker.repository;

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
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1 and a.start > CURRENT_DATE")
	public List<TutorServiceAppointment> getAllInCommingAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1")
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByUser(int tutorId);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Appointment a WHERE a.resort_id=?1",nativeQuery = true)
	public void deleteResortAppointments(int resortId);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Appointment save(Appointment a);
}
