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
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.TutorServiceAppointmentDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.mapper.CustomModelMapperAbstract;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.AppointmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	@Autowired
	private CustomModelMapper<TutorServiceAppointment, TutorServiceAppointmentDTO> tutorServiceModelMapper;

	@GetMapping("/appointments")
	public ResponseEntity<ArrayList<Appointment>> getAll() {
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
	@GetMapping("api/tutor-service/appointments")
	public ResponseEntity<ArrayList<TutorServiceAppointment>> getAllTutorServiceAppointments() {
		return ResponseEntity.ok((ArrayList<TutorServiceAppointment>) service.getTutorServiceApointments());
	}

	@PostMapping("api/appointments/tutor-service")
	public ResponseEntity addTutorServiceAppointment(@RequestBody TutorServiceAppointmentDTO dto) {
		TutorServiceAppointment appointment = tutorServiceModelMapper.convertToEntity(dto);
		//appointment.setUser(new User(1)); // TODO: Get id of loggedin user
		service.addNewTutorServiceAppointment(appointment);
		return ResponseEntity.ok(tutorServiceModelMapper.convertToDto(appointment));
	}

	@GetMapping("api/users/tutors/{idtutor}/tutor-service/appointments")
	public ResponseEntity getAllAppointmentsByTutor(@PathVariable("idtutor") Integer idtutor) {
		return ResponseEntity
				.ok(tutorServiceModelMapper.convertToDtos(service.getAllTutorServiceAppointmentsByTutor(idtutor)));
	}
	
	@GetMapping("/getPendingAppointments")
	public ResponseEntity<List<Appointment>>  getPendingAppointments(){
		return ResponseEntity.ok((List<Appointment>)service.getPendingApointments());
	}

	@PostMapping("/makeBoatReservation")
	public ResponseEntity<?> makeBoatReservation(@RequestBody BoatAppointment appointment){
		return ResponseEntity.ok(service.makeBoatReservation(appointment));
	}
	
	@PostMapping("/makeResortReservation")
	public ResponseEntity<?> makeResortReservation(@RequestBody ResortAppointment appointment){
		return ResponseEntity.ok(service.makeResortReservation(appointment));
	}
	
	@PostMapping("/makeTutorServiceReservation")
	public ResponseEntity<?> makeTutorServiceReservation(@RequestBody TutorServiceAppointment appointment){
		return ResponseEntity.ok(service.makeTutorServiceReservation(appointment));
	}
	
	@PostMapping("cancelReservation")
	public ResponseEntity<?> cancelReservation(@RequestBody Integer id){
		return ResponseEntity.ok(service.cancelReservation(id));
	}
}
