package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.ClientRegistrationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/register")
public class ClientRegistrationController {
	@Autowired
	ClientRegistrationService service;
	
	@PostMapping("")
	public String Register(@RequestBody User user) {
		service.Register(user);
		return "radi";
	}
	
}
