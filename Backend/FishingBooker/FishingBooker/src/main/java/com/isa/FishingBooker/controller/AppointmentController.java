package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/resortAppointments")
	public ResponseEntity<ArrayList<ResortAppointment>> getAllResort() {
		return ResponseEntity.ok((ArrayList<ResortAppointment>) service.getResortApointments());
	}

	@GetMapping("/boatAppointments")
	public ResponseEntity<ArrayList<BoatAppointment>> getAllBoat() {
		return ResponseEntity.ok((ArrayList<BoatAppointment>) service.getBoatApointments());
	}

	@GetMapping("api/tutor-service/appointments")
	public ResponseEntity<ArrayList<TutorServiceAppointment>> getAllTutorServiceAppointments() {
		return ResponseEntity.ok((ArrayList<TutorServiceAppointment>) service.getTutorServiceApointments());
	}

	@PostMapping("api/appointments/tutor-service/{idservice}")
	public ResponseEntity addTutorServiceAppointment(@RequestBody TutorServiceAppointmentDTO dto,
			@PathVariable("idservice") Integer idService) {
		TutorServiceAppointment appointment= tutorServiceModelMapper.convertToEntity(dto);
		appointment.setTutorService(new TutorService(idService));
		appointment.setUser(new User(1)); // TODO: Get id of loggedin user
		service.addNew(appointment);
		return ResponseEntity.ok(tutorServiceModelMapper.convertToDto(appointment));
	}

	@GetMapping("api/users/tutors/{idtutor}/tutor-service/appointments")
	public ResponseEntity getAllAppointmentsByTutor(@RequestBody TutorServiceAppointment appointment,
			@PathVariable("idtutor") Integer idtutor) {
		return ResponseEntity.ok(tutorServiceModelMapper.convertToDtos(service.getAllTutorServiceAppointmentsByTutor(idtutor)));
	}

}
