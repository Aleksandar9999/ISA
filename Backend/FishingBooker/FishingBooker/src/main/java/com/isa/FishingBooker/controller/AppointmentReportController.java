package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.AppointmentReportDTO;
import com.isa.FishingBooker.dto.ReasonDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.AppointmentReport;
import com.isa.FishingBooker.service.AppointmentReportService;

@RestController
public class AppointmentReportController {

	@Autowired
	private AppointmentReportService service;
	@Autowired
	private CustomModelMapper<AppointmentReport, AppointmentReportDTO> mapper;
	private final String api="api/appointment-report";
	@PostMapping(api+"/bad-comment")
	public ResponseEntity<?> addBadCommentReport(@RequestBody AppointmentReportDTO report) {
		service.addBadCommentReport(mapper.convertToEntity(report));
		return ResponseEntity.ok().build();
	}
	@PostMapping(api+"/not-show-up")
	public ResponseEntity<?> addNotShopUpReport(@RequestBody AppointmentReportDTO report) {
		service.addNotShopUpReport(mapper.convertToEntity(report));
		return ResponseEntity.ok().build();
	}
	@PostMapping(api+"/ok-comment")
	public ResponseEntity<?> addOkCommentReport(@RequestBody AppointmentReportDTO report) {
		service.addOkCommentReport(mapper.convertToEntity(report));
		return ResponseEntity.ok().build();
	};
	
	@PutMapping(api+"/bad-comment/{id}/accept")
	public ResponseEntity<?> acceptBadReport(@PathVariable("id")int reportId) {
		service.acceptBadReport(reportId);
		return ResponseEntity.ok().build();
	};
	
	@PutMapping(api+"/bad-comment/{id}/reject")
	public ResponseEntity<?> rejectReportStatus(@PathVariable("id")int reportId,@RequestBody ReasonDTO reasonDto) {
		service.rejectBadReport(reportId,reasonDto.getReason());
		return ResponseEntity.ok().build();
	};
	
	@GetMapping(api)
	public ResponseEntity<?> getAll(){return ResponseEntity.ok(service.getAll());}
	
}
