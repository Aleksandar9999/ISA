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

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.service.BoatsService;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsController {
    @Autowired
	private BoatsService boatsService;
	
	@GetMapping("/boats")
	public ResponseEntity<ArrayList<Boat>> getAll() {
		return ResponseEntity.ok((ArrayList<Boat>) boatsService.getAll());
	}
	
	@PostMapping("/boats")
	public ResponseEntity<Boat> addNew(@RequestBody Boat boat) {
		boatsService.addNew(boat);
		return ResponseEntity.ok(boat);
	}
	
	@GetMapping("/boats/{id}")
	public ResponseEntity<Boat>  getBoatByID(@PathVariable Integer id) {
		return ResponseEntity.ok(boatsService.getById(id));
	}
}
