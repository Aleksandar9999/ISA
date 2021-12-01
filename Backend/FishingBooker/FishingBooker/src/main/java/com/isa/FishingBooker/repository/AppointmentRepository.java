package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1")
	public List<TutorServiceAppointment> getAllAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1 and a.tutorService.id=?2")
	public List<TutorServiceAppointment> getAllAppointmentsByTutorAndTutorService(int tutorId,int serviceId);
	
}
