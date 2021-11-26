package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.TutorServicesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesController {
    @Autowired
	private TutorServicesService tutorServicesService;
	
	@GetMapping("api/users/tutors/services")
	public ResponseEntity<ArrayList<TutorService>> getAll() {
		return ResponseEntity.ok((ArrayList<TutorService>) tutorServicesService.getAll());
	}
	
	@PostMapping("api/users/tutors/services")
	public ResponseEntity<TutorService> addNew(@RequestBody TutorService service) {
		tutorServicesService.addNew(service);
		return ResponseEntity.ok(service);
	}
	
	@GetMapping("api/users/tutors/services/{id}")
	public ResponseEntity<TutorService> getByID(@PathVariable("id") Integer id){	
		return ResponseEntity.ok(tutorServicesService.getById(id));
	}
}
