package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.exceptions.RegistrationException;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
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
	public ResponseEntity addNew(@RequestBody Tutor tutor) {
		try {
			usersService.addNew(tutor);
			return ResponseEntity.ok(tutor);
		}catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@PostMapping("api/users/tutors/delete")
	public ResponseEntity createDeleteProfileRequest() {
		//TODO: Bice prijavljen, uzmem ID iz JWT i napravim zahtijev DeleteRequest
		return null;
	}
	
	@GetMapping("api/users/tutors/{idtutor}")
	public ResponseEntity getTutorById(@PathVariable("idtutor") int tutor) {
		return ResponseEntity.ok(usersService.getTutorById(tutor));
	}
	
	@GetMapping("api/users/tutors/{idtutor}/services")
	public List<TutorService> getServicesTutorById(@PathVariable("idtutor") int id) {
		Tutor tutor=usersService.getTutorById(id);
		List<TutorService> services=new ArrayList<TutorService>();
		tutor.getServices().stream().forEach(s->services.add(s));
		return services;
	}
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody LoginInfoDTO user) {
		return ResponseEntity.ok(usersService.Login(user));
	}

	@PostMapping("register")
	public ResponseEntity register(@RequestBody RegistrationDTO user) {
		try {
			User newUser=usersService.Register(user);
			return ResponseEntity.ok(newUser);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}

	}

}
