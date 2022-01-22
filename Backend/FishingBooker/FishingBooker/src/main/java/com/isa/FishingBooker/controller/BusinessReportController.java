package com.isa.FishingBooker.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.BusinessReportAppointmentsAdminDTO;
import com.isa.FishingBooker.dto.BusinessReportAppointmentsDTO;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.service.AppointmentService;
import com.isa.FishingBooker.service.SystemDataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BusinessReportController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private SystemDataService systemService;
	@PreAuthorize("hasAnyRole('TUTOR', 'ADMIN')")
	@GetMapping("api/business-report/tutor/{id}/appointments")
	public ResponseEntity<?> getTutorReport(@PathVariable("id") int tutorId,
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			List<TutorServiceAppointment> appointmentsInPeriod = appointmentService
					.getAllByTutorAndPeriod(tutorId, Date.valueOf(startDate), Date.valueOf(endDate));
			return ResponseEntity.ok(new BusinessReportAppointmentsDTO<TutorServiceAppointment>(appointmentsInPeriod));
		}
		return ResponseEntity.ok(appointmentService.getAllTutorServiceAppointmentsByTutor(tutorId));
	}
	@PreAuthorize("hasAnyRole('TUTOR')")
	@GetMapping("api/business-report/tutor/appointments/me")
	public ResponseEntity<?> getReportForLoggedin(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		return getTutorReport(UsersController.getLoggedInUserId(), startDate, endDate);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("api/business-report/appointments")
	public ResponseEntity<?> getAdminBusinessReport(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			List<Appointment> appointmentsInPeriod = appointmentService.getAllInPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
			return ResponseEntity.ok(new BusinessReportAppointmentsAdminDTO<Appointment>(appointmentsInPeriod,systemService.getById(1).getProcentage()));
		}
		return null;
	}	
}
