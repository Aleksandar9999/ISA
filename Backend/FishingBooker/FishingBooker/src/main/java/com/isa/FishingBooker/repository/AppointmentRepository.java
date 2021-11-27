package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Appoinment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appoinment ,Integer> {
	
	@Query("select * from appoinment a where dtype='ResortAppointment'")
	public List<Appoinment> getAllResortAppoints();
	
	@Query("select * from appoinment a where dtype='BoatAppointment'")
	public List<Appoinment> getAllBoatAppoints();
	
	@Query("select * from appoinment a where dtype='TutorServiceAppointment'")
	public List<Appoinment> getAllTutorServiceAppoints();
	
}
