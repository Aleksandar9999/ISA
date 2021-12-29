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
	@GetMapping("/appointments/tutor")
	public ResponseEntity<?> getAll(@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate,
			@RequestParam(name = "type", defaultValue = "") String calendarType) {
		int loggedinUserId = UsersController.getLoggedInUserId();
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			switch (calendarType) {
			case "year":
				return ResponseEntity.ok(yearCalendarMapper.convertToDtos(
						service.getAllByTutorAndPeriod(4, Date.valueOf(startDate), Date.valueOf(endDate))));
			case "month":
				return ResponseEntity.ok(monthCalendarMapper.convertToDtos(
						service.getAllByTutorAndPeriod(4, Date.valueOf(startDate), Date.valueOf(endDate)),
						LocalDate.parse(startDate), LocalDate.parse(endDate)));
			case "week":

				break;

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

	@GetMapping("/getPendingAppointments")
	public ResponseEntity<List<Appointment>> getPendingAppointments(@RequestBody String email) {
		return ResponseEntity.ok((List<Appointment>) service.getPendingApointments(email));
	}
}
