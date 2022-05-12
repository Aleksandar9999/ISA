package com.isa.FishingBooker.controller;

import java.sql.Date;
import java.util.List;

import com.isa.FishingBooker.dto.BusinessReportDTO;
import com.isa.FishingBooker.model.CompletedAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.SystemDataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BusinessReportController {

	@Autowired
	private AppointmentService appointmentService;
	
	@PreAuthorize("hasAnyRole('TUTOR', 'ADMIN')")
	@GetMapping("api/business-report/tutor/{id}/appointments")
	public ResponseEntity<?> getTutorReport(@PathVariable("id") int tutorId,
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			List appointmentsInPeriod = appointmentService
					.getAllByTutorAndPeriod(tutorId, Date.valueOf(startDate), Date.valueOf(endDate));
			List<CompletedAppointment> completedAppointments=appointmentService.getAllCompletedAppointmentsInPeriodByTutorId(tutorId,Date.valueOf(startDate), Date.valueOf(endDate));
			return ResponseEntity.ok(new BusinessReportDTO(completedAppointments,appointmentsInPeriod));
		}
		return ResponseEntity.ok(appointmentService.getAllTutorServiceAppointmentsByTutor(tutorId));
	}
	@PreAuthorize("hasAnyRole('TUTOR')")
	@GetMapping("api/business-report/me")
	public ResponseEntity<?> getReportForLoggedin(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		return getTutorReport(UsersController.getLoggedInUserId(), startDate, endDate);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("api/business-report")
	public ResponseEntity<?> getAdminBusinessReport(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			List<CompletedAppointment> completedAppointmentsInPeriod = appointmentService.getAllCompletedAppointmentsInPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
			List allAppointments = appointmentService.getAllInPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
			return ResponseEntity.ok(new BusinessReportDTO(completedAppointmentsInPeriod,allAppointments));
		}
		return null;
	}	
}
