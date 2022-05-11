package com.isa.FishingBooker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.service.interfaces.PeriodService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PeriodsController {
	@Autowired
	private PeriodService service;
	
	@GetMapping("/periods")
	public ResponseEntity<List<Period>> getPeriods(){
		return ResponseEntity.ok(service.getAll());
	}
	
}
