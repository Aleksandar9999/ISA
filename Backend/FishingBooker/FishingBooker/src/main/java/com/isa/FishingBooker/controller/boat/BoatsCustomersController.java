package com.isa.FishingBooker.controller.boat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.service.interfaces.BoatsService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsCustomersController {
	@Autowired
	protected BoatsService boatsService;

	@GetMapping("api/boats/{id}/customers")
	public ResponseEntity<?> getAllCustomers(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.boatsService.getById(id).getCustomers());
	}

	@PostMapping("api/boats/{id}/customers")
	public ResponseEntity<?> addNewCustomer(@PathVariable("id") int boatId) {
		boatsService.addNewCustomer(boatId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("api/boats/{id}/customers")
	public ResponseEntity<?> removeCustomer(@PathVariable("id") int boatId) {
		boatsService.removeCustomer(boatId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("api/boats/{id}/customers/me")
	public ResponseEntity<?> isLoggedinUserCustomer(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.boatsService.getById(id).getCustomers().stream()
				.anyMatch(el -> el.getId().equals(UsersController.getLoggedInUserId())));
	}
}
