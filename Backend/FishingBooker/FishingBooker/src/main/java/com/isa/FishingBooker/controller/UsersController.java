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
import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.EmailService;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;

	@Autowired EmailService emailService;

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
	
	@GetMapping("api/users/tutors/{id}/available-periods")
	public ResponseEntity getTutorAvailablePeriods(@PathVariable("id") int id) {
		Tutor tutor=usersService.getTutorById(id);//TODO:FIX POTENTIAL BUG
		return ResponseEntity.ok(tutor.getAvailabilityPeriods());
	}
	
	@PostMapping("api/users/tutors/available-periods")
	public ResponseEntity addPeriod(@RequestBody Period period) {
		Tutor tutor=usersService.getTutorById(4);//TODO:FIX HARDCODE
		tutor.addPeriod(period);
		usersService.update(tutor);
		return ResponseEntity.ok(period);
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
			emailService.sendRegisterConfirmationMail(user);
			return ResponseEntity.ok(newUser);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}

	}

}
