package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.AppointmentReport;

@Repository
public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Integer> {
	@Query("select ar from AppointmentReport ar where ar.appointment.id=?1")
	public AppointmentReport getByAppointmentId(int id);
}
