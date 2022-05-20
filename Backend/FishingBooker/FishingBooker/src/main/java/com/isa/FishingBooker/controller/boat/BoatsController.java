package com.isa.FishingBooker.controller.boat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.dto.BoatDTO;
import com.isa.FishingBooker.dto.TutorServiceDTO;
import com.isa.FishingBooker.exceptions.AuthorizationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsController {
	@Autowired
	protected BoatsService boatsService;
	@Autowired
	private CustomModelMapper<Boat, BoatDTO> boatMapper;

	@GetMapping("api/boats")
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.ok(boatMapper.convertToDtos((ArrayList<Boat>) boatsService.getAll()));
	}

	@GetMapping("api/users/boatowners/boats") // za sve brodove korisnika
	public ResponseEntity<List<Boat>> getAllBoats() {
		return ResponseEntity.ok(boatsService.getAll());
	}

	
	@GetMapping("api/users/boatowners/boats/{id}")
	public ResponseEntity<Boat> getBoat(@PathVariable("id")Integer id) 
	{ return ResponseEntity.ok(boatsService.getById(id)); }
	 

	@GetMapping("api/boats/valid")
	public ResponseEntity<?> getAllValid() {
		return ResponseEntity.ok(boatMapper.convertToDtos(boatsService.getAllValid()));
	}

	@GetMapping("api/users/boatowners/{idboatowner}/boats")
	public ResponseEntity<?> getBoatsBoatOwnerById(@PathVariable("idboatowner") int id) {
		return ResponseEntity.ok(boatMapper.convertToDtos(boatsService.getAllValidByBoatOwner(id)));
	}

	@GetMapping("api/boats/{idboat}")
	public ResponseEntity<?> getBoatOwnerBoat(@PathVariable("idboat") int idboat) {
		return ResponseEntity
				.ok(boatMapper.convertToDto((Boat) boatsService.getById(idboat)));
	}

	@PostMapping("api/boats")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> addBoat(@RequestBody BoatDTO dto) {
		Boat entity = boatMapper.convertToEntity(setLoggedinBoatOwner(dto));
		boatsService.addNew(entity);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("api/boats/{idboat}")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> updateBoat(@RequestBody BoatDTO dto,
			@PathVariable("idboat") int idboat) {
		Boat entity = boatMapper.convertToEntity(dto);
		validateBoatOwner(entity);
		boatsService.updateInfo(entity);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("api/boats/{id}")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> deleteBoat(@PathVariable("id") int id) {
		validateBoatOwner(boatsService.getById(id));
		boatsService.delete(id);
		return ResponseEntity.ok().build();
	}

	private void validateBoatOwner(Boat entity) {
		if (!entity.getBoatOwner().getId().equals(UsersController.getLoggedInUserId()))
			throw new AuthorizationException();
	}

	private BoatDTO setLoggedinBoatOwner(BoatDTO dto) {
		dto.setBoatOwnerId(UsersController.getLoggedInUserId());
		return dto;
	}
}
