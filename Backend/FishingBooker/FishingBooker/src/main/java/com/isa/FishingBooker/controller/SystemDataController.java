package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.SystemData;
import com.isa.FishingBooker.service.SystemDataService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SystemDataController {
	
	@Autowired
	private SystemDataService service;
	
	@GetMapping("api/system-data")
	public ResponseEntity getSystemData() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("api/system-data")
	public ResponseEntity addSystemData(@RequestBody SystemData systemData) {
		service.addNew(systemData);
		return ResponseEntity.ok(systemData);
	}
	
}
