package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService service;
	
	@PostMapping("")
	public String Login(@RequestBody User user) {
		return service.Login(user);		
	}
	
	
}
