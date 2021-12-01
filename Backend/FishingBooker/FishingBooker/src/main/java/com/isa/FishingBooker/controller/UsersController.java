package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.EmailService;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired 
	EmailService emailService;
	
	@GetMapping("api/users")
	public ResponseEntity<ArrayList<User>> getAll() {
		return ResponseEntity.ok((ArrayList<User>) usersService.getAll());
	}
	
	@PostMapping("api/users/tutors")
	public ResponseEntity<Tutor> addNew(@RequestBody Tutor tutor) {
		usersService.addNew(tutor);
		return ResponseEntity.ok(tutor);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody LoginInfoDTO user) {
		return ResponseEntity.ok(usersService.Login(user));
	}
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegistrationDTO user){
		emailService.sendRegisterConfirmationMail(user);
		return ResponseEntity.ok(usersService.Register(user));
	}
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(usersService.getById(id));
	}
	
}
