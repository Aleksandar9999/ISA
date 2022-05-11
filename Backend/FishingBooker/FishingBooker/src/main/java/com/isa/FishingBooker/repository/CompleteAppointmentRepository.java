package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.CompletedAppointment;

@Repository
public interface CompleteAppointmentRepository extends JpaRepository<CompletedAppointment, Integer>{

}