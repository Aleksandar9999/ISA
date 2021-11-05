package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;
	
	@GetMapping("api/users")
	public ResponseEntity<ArrayList<User>> getAll() {
		return ResponseEntity.ok((ArrayList<User>) usersService.getAll());
	}
	
	@PostMapping("api/users/tutors")
	public ResponseEntity<Tutor> addNew(@RequestBody Tutor tutor) {
		usersService.addNew(tutor);
		return ResponseEntity.ok(tutor);
	}
	
}
