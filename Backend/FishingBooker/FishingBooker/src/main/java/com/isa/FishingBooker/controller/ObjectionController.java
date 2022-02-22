package com.isa.FishingBooker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.service.ObjectionService;
import com.isa.FishingBooker.dto.ObjectionDTO;
import com.isa.FishingBooker.dto.ObjectionResponseDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ObjectionController {
	
	@Autowired
	private CustomModelMapper<Objection, ObjectionDTO> mapper;
	
	@Autowired
	private ObjectionService service;
	
	@GetMapping("/objections")
	public ResponseEntity<List<Objection>> getObjections(){
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/sendObjection")
	public ResponseEntity<Objection> addObjection(@RequestBody Objection o) {
		service.addNew(o);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/objections/{id}")
	public ResponseEntity<?> updateObjection(@RequestBody ObjectionResponseDTO dto) {
		service.sendAdminResponsEmails(mapper.convertToEntity(dto), dto.getResponse(), new Admin(UsersController.getLoggedInUserId()));
		return ResponseEntity.ok().build();
	}
}
