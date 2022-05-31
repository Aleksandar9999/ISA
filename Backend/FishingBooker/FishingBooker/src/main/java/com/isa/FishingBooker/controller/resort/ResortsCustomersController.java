package com.isa.FishingBooker.controller.resort;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.ResortsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortsCustomersController {
	@Autowired
	protected ResortsService resortsService;

	@GetMapping("api/resorts/{id}/customers")
	public ResponseEntity<?> getAllCustomers(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.resortsService.getById(id).getCustomers());
	}

	@PostMapping("api/resorts/{id}/customers")
	public ResponseEntity<?> addNewCustomer(@PathVariable("id") int resortId) {
		resortsService.addNewCustomer(resortId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("api/resorts/{id}/customers")
	public ResponseEntity<?> removeCustomer(@PathVariable("id") int resortId) {
		resortsService.removeCustomer(resortId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("api/resorts/{id}/customers/me")
	public ResponseEntity<?> isLoggedinUserCustomer(@PathVariable("id") int id) {
		Set<User> customers=this.resortsService.getById(id).getCustomers();
		boolean subscribed =customers.stream()
				.anyMatch(el -> el.getId().equals(UsersController.getLoggedInUserId()));
		return subscribed?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
