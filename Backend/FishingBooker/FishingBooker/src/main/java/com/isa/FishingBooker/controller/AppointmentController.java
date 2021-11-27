package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Appoinment;
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
	public ResponseEntity<ArrayList<Appoinment>>  getAllResort(){
		return ResponseEntity.ok((ArrayList<Appoinment>)service.getResortApointments());
	}
	
	@GetMapping("/boatAppointments")
	public ResponseEntity<ArrayList<Appoinment>>  getAllBoat(){
		return ResponseEntity.ok((ArrayList<Appoinment>)service.getBoatApointments());
	}
	
	@GetMapping("/tutorServiceAppointments")
	public ResponseEntity<ArrayList<Appoinment>>  getAllTutorService(){
		return ResponseEntity.ok((ArrayList<Appoinment>)service.getTutorServiceApointments());
	}
	
	
}
