package com.isa.FishingBooker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Extras;
import com.isa.FishingBooker.service.interfaces.ExtrasService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ExtrasController {
	@Autowired
	private ExtrasService service;
	
	
	@GetMapping("/extras")
	public ResponseEntity<List<Extras>> getExtras(){
		return ResponseEntity.ok(service.getAll());
	}
}
