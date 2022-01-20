package com.isa.FishingBooker.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.BusinessReportAppointmentsDTO;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.service.AppointmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BusinessReportController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("api/business-report/tutor/{id}/appointments")
	public ResponseEntity<?> getAllCalendarWeek(@PathVariable("id") int tutorId,
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			LocalDate endDateLocal = Date.valueOf(startDate).toLocalDate().plusDays(7);
			List<TutorServiceAppointment> appointmentsInPeriod = appointmentService
					.getAllByTutorAndPeriod(tutorId, Date.valueOf(startDate), Date.valueOf(endDateLocal));
			return ResponseEntity.ok(new BusinessReportAppointmentsDTO<TutorServiceAppointment>(appointmentsInPeriod));
		}
		return ResponseEntity.ok(appointmentService.getAllTutorServiceAppointmentsByTutor(tutorId));
	}

}
