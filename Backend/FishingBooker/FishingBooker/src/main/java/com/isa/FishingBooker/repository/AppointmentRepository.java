package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Appoinment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appoinment ,Integer> {
	
	@Query(value="select start, duration, max_person, additional_services, price, address_id, resort_id, user_id from appoinment a where a.dtype=ResortAppointment", nativeQuery=true)
	public List<ResortAppointment> getAllResortAppoints();
	
	@Query(value="select start, duration, max_person, additional_services, price, address_id, boat_id, user_id from appoinment a where a.dtype=BoatAppointment", nativeQuery=true)
	public List<BoatAppointment> getAllBoatAppoints();
	
	@Query(value="select start, duration, max_person, additional_services, price, address_id, tutor_service_id, user_id from appoinment a where a.dtype=TutorServiceAppointment", nativeQuery=true)
	public List<TutorServiceAppointment> getAllTutorServiceAppoints();
	
}
