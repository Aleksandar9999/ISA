package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.TutorServiceAppointmentDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.mapper.YearCalendarMapper;
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

	@Autowired
	private YearCalendarMapper yearCalendarMapper;
	
	@GetMapping("/appointments")
	public ResponseEntity<?> getAll(@RequestParam(name = "year",defaultValue = "0") int year) {
		if (year != 0) {
			return ResponseEntity.ok(yearCalendarMapper.convertToDtos(service.getAllByTutorAndYear(4, year), year));
		}
		return ResponseEntity.ok((ArrayList<Appointment>) service.getAll());
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
