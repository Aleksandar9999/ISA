package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.ServicePrice;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesPricesController {
	@Autowired
	protected TutorServicesService tutorServicesService;

	@GetMapping("api/tutor-services/{idservice}/prices")
	public ResponseEntity<?> getTutorServicePrice(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getPrices());
	}

	@PostMapping("api/tutor-services/{idservice}/prices")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorServicePrice(@RequestBody ServicePrice price,
			@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		tutorService.addPrice(price);
		tutorServicesService.update(tutorService);
		return ResponseEntity.ok(price);
	}
}
