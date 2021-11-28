package com.isa.FishingBooker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.service.ObjectionService;
import com.isa.FishingBooker.model.Objection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ObjectionController {
	
	@Autowired
	private ObjectionService service;
	
	@GetMapping("/objections")
	public ResponseEntity<List<Objection>> getObjections(){
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/sendObjection")
	public ResponseEntity<Objection> addObjection(@RequestBody Objection o) {
		return ResponseEntity.ok(service.addObjection(o));
	}
	
}
