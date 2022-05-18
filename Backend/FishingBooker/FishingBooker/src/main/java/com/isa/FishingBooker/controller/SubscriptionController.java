package com.isa.FishingBooker.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.SubscriptionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/subscripeBoat")
	public ResponseEntity<?> subscripeBoatOnDiscountOffers(@RequestBody Boat boat) {
		return ResponseEntity.ok(service.subscribeBoat(boat));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/subscripeResort")
	public ResponseEntity<?> subscripeResortOnDiscountOffers(@RequestBody Resort resort) {
		return ResponseEntity.ok(service.subscribeResort(resort));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/subscripeTutorService")
	public ResponseEntity<?> subscripeTutorOnDiscountOffers(@RequestBody TutorService tutorService) {
		return ResponseEntity.ok(service.subscribeTutorService(tutorService));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/cancelTutorServiceSubscription")
	public ResponseEntity<?> cancelAdventureSubscription(@RequestBody TutorService tutorService) {
		return ResponseEntity.ok(service.cancelAdventureSubscription(tutorService));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/cancelBoatSubscription")
	public ResponseEntity<?> cancelBoatSubscription(@RequestBody Boat boat) {
		return ResponseEntity.ok(service.cancelBoatSubscription(boat));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/cancelResortSubscription")
	public ResponseEntity<?> cancelResortSubscription(@RequestBody Resort resort) {
		return ResponseEntity.ok(service.cancelResortSubscritpion(resort));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getSubscriptions")
	public ResponseEntity<?> getSubscriptions() {
		return ResponseEntity.ok(service.getSubscriptionForUser());
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/api/subscriptions/tutor-service/{id}")
	public ResponseEntity<?> isSubscribed(@PathVariable("id") int id) {
		return service.getSubscriptionForUser().getTutorServices().stream().filter(ts -> ts.getId().equals(id))
				.collect(Collectors.toList()).size() == 0 ? ResponseEntity.notFound().build()
						: ResponseEntity.ok().build();
	}

}
