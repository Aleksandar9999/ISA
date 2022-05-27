package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.AppointmentReportDTO;
import com.isa.FishingBooker.dto.ReasonDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.AppointmentReport;
import com.isa.FishingBooker.service.interfaces.AppointmentReportService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppointmentReportController {

	@Autowired
	private AppointmentReportService service;
	@Autowired
	private CustomModelMapper<AppointmentReport, AppointmentReportDTO> mapper;
	private final String api = "api/appointment-report";
	private final String apiboat = "api/boat/appointment-report";


	@PreAuthorize("hasRole('TUTOR')")
	@PostMapping(api + "/bad-comment")
	public ResponseEntity<?> addBadCommentReport(@RequestBody AppointmentReportDTO report) {
		service.addBadCommentReport(mapper.convertToEntity(report),report.getAppointmentId());
		return ResponseEntity.ok().build();
	}


	@PreAuthorize("hasRole('TUTOR')")
	@PostMapping(api + "/not-show-up")
	public ResponseEntity<?> addNotShopUpReport(@RequestBody AppointmentReportDTO report) {
		service.addNotShopUpReport(report.getAppointmentId());
		return ResponseEntity.ok().build();
	}


	@PreAuthorize("hasRole('TUTOR')")
	@PostMapping(api + "/ok-comment")
	public ResponseEntity<?> addOkCommentReport(@RequestBody AppointmentReportDTO report) {
		service.addOkCommentReport(report.getAppointmentId());
		return ResponseEntity.ok().build();
	};
	
	
	//boat
	@PreAuthorize("hasRole('BOATOWNER')")
	@PostMapping(apiboat + "/bad-comment")
	public ResponseEntity<?> addBadCommentReportBoat(@RequestBody AppointmentReportDTO report) {
		service.addBadCommentReport(mapper.convertToEntity(report),report.getAppointmentId());
		return ResponseEntity.ok().build();
	}


	@PreAuthorize("hasRole('BOATOWNER')")
	@PostMapping(apiboat + "/not-show-up")
	public ResponseEntity<?> addNotShopUpReportBoat(@RequestBody AppointmentReportDTO report) {
		service.addNotShopUpReport(report.getAppointmentId());
		return ResponseEntity.ok().build();
	}


	@PreAuthorize("hasRole('BOATOWNER')")
	@PostMapping(apiboat + "/ok-comment")
	public ResponseEntity<?> addOkCommentReportBoat(@RequestBody AppointmentReportDTO report) {
		service.addOkCommentReport(report.getAppointmentId());
		return ResponseEntity.ok().build();
	};
	
	@PreAuthorize("hasRole('BOATOWNER')")
	@GetMapping(apiboat + "/appointment/{id}")
	public ResponseEntity<?> isReportCreatedBoat(@PathVariable("id") int id) {
		if (service.getByAppointmentId(id) == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(api + "/bad-comment/{id}/accept")
	public ResponseEntity<?> acceptBadReport(@PathVariable("id") int reportId) {
		service.acceptBadReport(reportId,(Admin) UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	};

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(api + "/bad-comment/{id}/reject")
	public ResponseEntity<?> rejectReportStatus(@PathVariable("id") int reportId, @RequestBody ReasonDTO reasonDto) {
		service.rejectBadReport(reportId, reasonDto.getReason(),(Admin) UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	};

	@PreAuthorize("hasAnyRole('TUTOR', 'ADMIN','BOATOWNER')")
	@GetMapping(api)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PreAuthorize("hasRole('TUTOR')")
	@GetMapping(api + "/appointment/{id}")
	public ResponseEntity<?> isReportCreated(@PathVariable("id") int id) {
		if (service.getByAppointmentId(id) == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}
}
