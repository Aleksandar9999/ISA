package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.dto.UserInfoDTO;
import com.isa.FishingBooker.exceptions.RegistrationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Status;
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
	
	@Autowired
	private CustomModelMapper<User, UserInfoDTO> userInfoMapper;
	
	@Autowired
	EmailService emailService;

	@GetMapping("api/users")
	public ResponseEntity<ArrayList<User>> getAll() {
		return ResponseEntity.ok((ArrayList<User>) usersService.getAll());
	}

	@PostMapping("api/users/tutors")
	public ResponseEntity addNew(@RequestBody Tutor tutor) {
		try {
			usersService.addNew(tutor);
			return ResponseEntity.ok(tutor);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}

	@GetMapping("api/users/tutors/{id}/available-periods")
	public ResponseEntity getTutorAvailablePeriods(@PathVariable("id") int id) {
		Tutor tutor = usersService.getTutorById(id);// TODO:FIX POTENTIAL BUG
		return ResponseEntity.ok(tutor.getAvailabilityPeriods());
	}

	@PostMapping("api/users/tutors/{id}/available-periods")
	public ResponseEntity addPeriod(@RequestBody Period period,@PathVariable("id") int id) {
		Tutor tutor = usersService.getTutorById(id);// TODO:FIX HARDCODE
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
		Tutor tutor = usersService.getTutorById(id);
		List<TutorService> services = new ArrayList<TutorService>();
		tutor.getServices().stream().forEach(s -> services.add(s));
		return services;
	}

	@GetMapping("api/users/search")
	public ResponseEntity getAllUsersByStatus(@RequestParam(value = "status",defaultValue = "") Status s) {
		return ResponseEntity.ok(usersService.search(s));
	}

	@PutMapping("api/users/{id}")
	public ResponseEntity update(@RequestBody User user,@PathVariable("id") int id) {
		usersService.update(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("api/users/{id}")
	public ResponseEntity getUserById(@PathVariable String id){
		return ResponseEntity.ok(userInfoMapper.convertToDto(usersService.getById(getUserId(id))));
	}

	private Integer getUserId(String id) {
		Integer userId;
		if(id.equals("me")) userId=1;//TODO: Postaviti userId na id trenutno prijavljenog korisnika
		else userId=Integer.parseInt(id);
		return userId;
	}
	
	@PostMapping("confirm/{id}")
	public ResponseEntity<String> confirmAccount(@PathVariable Integer id){
		return ResponseEntity.ok(usersService.confirmAccount(id));
	}
	
	@GetMapping("api/users/clients")
	public ResponseEntity getAllClients() {
		return ResponseEntity.ok(userInfoMapper.convertToDtos(usersService.getAllClients()));
	}
}
