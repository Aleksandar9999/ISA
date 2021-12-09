package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.FishingBooker.dto.TutorServiceDTO;
import com.isa.FishingBooker.exceptions.AuthorizationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.ServicePrice;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.TutorServicesService;
import com.isa.FishingBooker.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesController {
	@Autowired
	private TutorServicesService tutorServicesService;

	@Autowired
	private CustomModelMapper<TutorService, TutorServiceDTO> tutorServiceMapper;

	@GetMapping("api/tutor-services")
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDtos((ArrayList<TutorService>) tutorServicesService.getAll()));
	}

	@GetMapping("api/users/tutors/services")
	public ResponseEntity<List<TutorService>> getAllServices() {
		return ResponseEntity.ok( tutorServicesService.getAll());
	}
	
	
	@GetMapping("api/users/tutors/services/{id}")
	public ResponseEntity<TutorService> getService(@PathVariable("id") Integer id) {
		return ResponseEntity.ok( tutorServicesService.getById(id));
	}
	
	@GetMapping("api/tutor-services/valid")
	public ResponseEntity<?> getAllValid() {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDtos((ArrayList<TutorService>) tutorServicesService.getAllValid()));
	}
	
	@GetMapping("api/users/tutors/{idtutor}/services")
	public ResponseEntity<?> getServicesTutorById(@PathVariable("idtutor") int id) {
		return ResponseEntity.ok(tutorServiceMapper.convertToDtos(tutorServicesService.getAllValidByTutor(id).stream().collect(Collectors.toList())));
	}
	
	@GetMapping("api/tutor-services/{idservice}")
	public ResponseEntity<?> getTutorService(@PathVariable("idservice") int idservice) {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDto((TutorService) tutorServicesService.getById(idservice)));
	}

	@PostMapping("api/users/tutors/{idtutor}/services")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorService(@PathVariable("idtutor") Integer idtutor,
			@RequestBody TutorServiceDTO dto) {
		TokenBasedAuthentication auth = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User tutor = (User) auth.getPrincipal();
		dto.setTutorId(tutor.getId());
		TutorService entity = tutorServiceMapper.convertToEntity(dto);
		tutorServicesService.addNew(entity);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("api/tutor-services/{idservice}")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> updateTutorService(@RequestBody TutorServiceDTO dto, @PathVariable("idservice") int idservice) {
		TutorService entity = tutorServiceMapper.convertToEntity(dto);
		try {
			validateTutor(entity);
			tutorServicesService.update(entity);
			return ResponseEntity.ok(entity);
		} catch (AuthorizationException ex) {
			return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
		}
	}

	@GetMapping("api/tutor-services/{idservice}/photos")
	public ResponseEntity<?> getTutorServicePhotos(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getPhotos());
	}

	@PostMapping("api/tutor-services/{idservice}/photos")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addNewPhoto(@RequestBody Photo photo, @PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		tutorService.addPhoto(photo);
		tutorServicesService.update(tutorService);
		return ResponseEntity.status(200).body("OK");
	}

	@GetMapping("api/tutor-services/{idservice}/discount-offers")
	public ResponseEntity<?> getTutorServiceDiscountOffers(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getDisconutOffers());
	}

	@PostMapping("api/tutor-services/{idservice}/discount-offers")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorServiceDiscountOffers(@RequestBody DiscountOffer offer, @PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		try {
			validateTutor(tutorService);
			tutorService.addDiscountOffer(offer);
			tutorServicesService.update(tutorService);
			return ResponseEntity.ok(offer);
		} catch (AuthorizationException ex) {
			return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
		}

	}

	@GetMapping("api/tutor-services/{idservice}/prices")
	public ResponseEntity<?> getTutorServicePrice(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getPrices());
	}

	@PostMapping("api/tutor-services/{idservice}/prices")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorServicePrice(@RequestBody ServicePrice price, @PathVariable("idservice") int idservice) {
			TutorService tutorService = tutorServicesService.getById(idservice);
			tutorService.addPrice(price);
			tutorServicesService.update(tutorService);
			return ResponseEntity.ok(price);
	}

	@DeleteMapping("api/tutor-services/{id}")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> deleteTutorService(@PathVariable("id") int id) {
		try {
			validateTutor(tutorServicesService.getById(id));
			tutorServicesService.delete(id);
			return ResponseEntity.ok().build();
		}catch(AuthorizationException ex) {
			return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());	
		}
	}

	private void validateTutor(TutorService entity) {
		TokenBasedAuthentication auth = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		if (!entity.getTutor().getId().equals(user.getId()))
			throw new AuthorizationException();
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/users/tutors/servicesforList")
	public ResponseEntity<List<TutorService>> getAllForList() {
		return ResponseEntity.ok(tutorServicesService.getAll());
	}
}
