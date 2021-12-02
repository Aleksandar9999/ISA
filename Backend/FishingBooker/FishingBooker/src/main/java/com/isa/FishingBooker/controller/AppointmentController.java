package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Appoinment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.service.AppointmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	
	@GetMapping("/appointments")
	public ResponseEntity<ArrayList<Appoinment>>  getAll(){
		return ResponseEntity.ok((ArrayList<Appoinment>)service.getAll());
	}
	
	@GetMapping("/resortAppointments")
	public ResponseEntity<ArrayList<ResortAppointment>>  getAllResort(){
		return ResponseEntity.ok((ArrayList<ResortAppointment>)service.getResortApointments());
	}
	
	@GetMapping("/boatAppointments")
	public ResponseEntity<ArrayList<BoatAppointment>>  getAllBoat(){
		return ResponseEntity.ok((ArrayList<BoatAppointment>)service.getBoatApointments());
	}
	
	@GetMapping("/tutorServiceAppointments")
	public ResponseEntity<ArrayList<TutorServiceAppointment>>  getAllTutorService(){
		return ResponseEntity.ok((ArrayList<TutorServiceAppointment>)service.getTutorServiceApointments());
	}
	
	@GetMapping("/getPendingAppointments")
	public ResponseEntity<List<Appoinment>>  getPendingAppointments(@RequestBody String email){
		return ResponseEntity.ok((List<Appoinment>)service.getPendingApointments(email));
	}
}
