package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.service.BoatsService;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsController {
    @Autowired
	private BoatsService boatsService;
	
    @GetMapping("api/users/boatowners/boats")
	public ResponseEntity<ArrayList<Boat>> getAll() {
		return ResponseEntity.ok((ArrayList<Boat>) boatsService.getAll());
	}

	@GetMapping("api/users/boatowners/{idboatowner}/boats/{idboat}")
	public ResponseEntity<Boat> getBoat(@PathVariable("idboatowner") Integer idboatowner, @PathVariable("idboat") Integer idboat){
		return ResponseEntity.ok((Boat) boatsService.getById(idboat));
	}

	@PostMapping("api/users/boatowners/{idboatowner}/boats")
	public ResponseEntity<String> addBoat(@PathVariable("idboatowner") Integer idboatowner){
		return ResponseEntity.ok("NotImplemented");
	}

	@PutMapping("api/users/boatowners/{idboatowner}/boats/{idboat}")
	public ResponseEntity<Boat> updateBoat(@PathVariable("idboatowner") Integer idboatowner, @PathVariable("idboat") Integer idboat){
		return ResponseEntity.ok((Boat) boatsService.getById(idboat));
	}
	
	@PostMapping("api/users/boatowners/boats")
	public ResponseEntity<Boat> addNew(@RequestBody Boat boat) {
		boatsService.addNew(boat);
		return ResponseEntity.ok(boat);
	}
	
	@GetMapping("api/users/boatowners/boats/{id}")
	public ResponseEntity<Boat> getByID(@PathVariable("id") Integer id){	
		return ResponseEntity.ok(boatsService.getById(id));
	}
	
	@DeleteMapping("/api/boats/{id}")
	public ResponseEntity<?> deleteBoat(@PathVariable int id){
		boatsService.delete(id);
		return ResponseEntity.ok().build();
	}
}
