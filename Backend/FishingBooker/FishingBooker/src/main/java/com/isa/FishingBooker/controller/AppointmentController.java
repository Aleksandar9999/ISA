package com.isa.FishingBooker.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.isa.FishingBooker.dto.TutorServiceAppointmentDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.mapper.calendar.MonthCalendarMapper;
import com.isa.FishingBooker.mapper.calendar.YearCalendarMapper;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.service.AppointmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	@Autowired
	private CustomModelMapper<TutorServiceAppointment, TutorServiceAppointmentDTO> tutorServiceModelMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	private YearCalendarMapper yearCalendarMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	private MonthCalendarMapper monthCalendarMapper;

	@SuppressWarnings("unchecked")
	@GetMapping("api/appointments/tutor")
	public ResponseEntity<?> getAll(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate,
			@RequestParam(name = "type", defaultValue = "") String calendarType) {
		int loggedinUserId = UsersController.getLoggedInUserId();
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			switch (calendarType) {
			case "year":
				return ResponseEntity.ok(yearCalendarMapper.convertToDtos(
						service.getAllByTutorAndPeriod(4, Date.valueOf(startDate), Date.valueOf(endDate))));//ТODO: CHANGE FIXED TUTOR ID
			case "month":
				return ResponseEntity.ok(monthCalendarMapper.convertToDtos(
						service.getAllByTutorAndPeriod(4, Date.valueOf(startDate), Date.valueOf(endDate)),
						LocalDate.parse(startDate), LocalDate.parse(endDate)));
			case "week":{
				LocalDate endDateLocal=Date.valueOf(startDate).toLocalDate().plusDays(7);
				return ResponseEntity.ok(service.getAllByTutorAndPeriod(4, Date.valueOf(startDate), Date.valueOf(endDateLocal)));
			}
			default:
				break;
			}
		}
		return ResponseEntity.ok(service.getAllTutorServiceAppointmentsByTutor(loggedinUserId));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/resortAppointments")
	public ResponseEntity<ArrayList<ResortAppointment>> getAllResort() {
		return ResponseEntity.ok((ArrayList<ResortAppointment>) service.getResortApointments());
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/boatAppointments")
	public ResponseEntity<ArrayList<BoatAppointment>> getAllBoat() {
		return ResponseEntity.ok((ArrayList<BoatAppointment>) service.getBoatApointments());
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/appointments/tutor-service")
	public ResponseEntity<ArrayList<TutorServiceAppointment>> getAllTutorServiceAppointments() {
		return ResponseEntity.ok((ArrayList<TutorServiceAppointment>) service.getTutorServiceApointments());
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("api/appointments/tutor-service")
	public ResponseEntity<?> addTutorServiceAppointment(@RequestBody TutorServiceAppointmentDTO dto) {
		TutorServiceAppointment appointment = tutorServiceModelMapper.convertToEntity(dto);
		service.addNewTutorServiceAppointment(appointment, dto.isValidateUser());
		return ResponseEntity.ok(tutorServiceModelMapper.convertToDto(appointment));
	}

	@GetMapping("api/users/tutors/{idtutor}/tutor-service/appointments")
	public ResponseEntity<?> getAllAppointmentsByTutor(@PathVariable("idtutor") Integer idtutor) {
		return ResponseEntity
				.ok(tutorServiceModelMapper.convertToDtos(service.getAllTutorServiceAppointmentsByTutor(idtutor)));
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getPendingAppointments")
	public ResponseEntity<List<Appointment>>  getPendingAppointments(){
		return ResponseEntity.ok((List<Appointment>)service.getPendingApointments());
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/makeBoatReservation")
	public ResponseEntity<?> makeBoatReservation(@RequestBody BoatAppointment appointment){
		return ResponseEntity.ok(service.makeBoatReservation(appointment));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/makeResortReservation")
	public ResponseEntity<?> makeResortReservation(@RequestBody ResortAppointment appointment){
		return ResponseEntity.ok(service.makeResortReservation(appointment));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/makeTutorServiceReservation")
	public ResponseEntity<?> makeTutorServiceReservation(@RequestBody TutorServiceAppointment appointment){
		return ResponseEntity.ok(service.makeTutorServiceReservation(appointment));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/makeQuickReservation/{id}")
	public ResponseEntity<?> makeQuickBoatReservation(@PathVariable("id") Integer id){
		return ResponseEntity.ok(service.makeQuickReservation(id));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("cancelReservation/{id}")
	public ResponseEntity<?> cancelReservation(@PathVariable("id") Integer id){
		return ResponseEntity.ok(service.cancelReservation(id));
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getAppointmentHistory")
	public ResponseEntity<?> odlAppointments(){
		return ResponseEntity.ok(service.getOldAppointments());
	}
	
	@GetMapping("/appointments/additionalServices/{boatId}")
	public ResponseEntity<?> additionalServicesListBoat(@PathVariable("boatId") Integer boatID){
		return ResponseEntity.ok(service.getAdditionalServicesForBoat(boatID));
	}
	
	@GetMapping("/appointments/getBoatPeriods/{boatId}")
	public ResponseEntity<?> getBoatPeriods(@PathVariable("boatId") Integer boatID){
		return ResponseEntity.ok(service.getBoatPeriods(boatID));
	}

}
