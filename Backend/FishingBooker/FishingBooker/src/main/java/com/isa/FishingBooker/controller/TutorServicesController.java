package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import com.isa.FishingBooker.dto.TutorServiceDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.ServicePrice;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.TutorServicesService;
import com.isa.FishingBooker.service.UsersService;

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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesController {
	@Autowired
	private TutorServicesService tutorServicesService;

	@Autowired
	private CustomModelMapper<TutorService, TutorServiceDTO> tutorServiceMapper;

	@GetMapping("api/users/tutors/services")
	public ResponseEntity getAll() {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDtos((ArrayList<TutorService>) tutorServicesService.getAll()));
	}

	@GetMapping("api/users/tutors/services/valid")
	public ResponseEntity getAllValid() {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDtos((ArrayList<TutorService>) tutorServicesService.getAllValid()));
	}

	@GetMapping("api/users/tutors/services/{id}")
	public ResponseEntity<TutorService> getByID(@PathVariable("id") Integer id) {
		TutorService tutorService = tutorServicesService.getById(id);
		return ResponseEntity.ok(tutorService);
	}

	@GetMapping("api/users/tutors/{idtutor}/services/{idservice}")
	public ResponseEntity getTutorService(@PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDto((TutorService) tutorServicesService.getById(idservice)));

	}

	@PostMapping("api/users/tutors/{idtutor}/services")
	public ResponseEntity addTutorService(@PathVariable("idtutor") Integer idtutor, @RequestBody TutorServiceDTO dto) {
		TutorService entity = tutorServiceMapper.convertToEntity(dto);
		entity.setTutor(new Tutor(idtutor));
		tutorServicesService.addNew(entity);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("api/users/tutors/{idtutor}/services/{idservice}")
	public ResponseEntity updateTutorService(@RequestBody TutorServiceDTO dto, @PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		TutorService entity = tutorServiceMapper.convertToEntity(dto);
		entity.setTutor(new Tutor(idtutor));
		entity.setId(idservice);
		tutorServicesService.update(entity);
		return ResponseEntity.ok(entity);
	}

	@GetMapping("api/users/tutors/{idtutor}/services/{idservice}/photos")
	public ResponseEntity getTutorServicePhotos(@PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		try {
			TutorService tutorService = tutorServicesService.getById(idservice);
			return ResponseEntity.status(200).body(tutorService.getPhotos());
		} catch (Exception e) {
			return ResponseEntity.ok(new ArrayList<>());
		}
	}

	@PostMapping("api/users/tutors/{idtutor}/services/{idservice}/photos")
	public ResponseEntity addNewPhoto(@RequestBody Photo photo, @PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		tutorService.addPhoto(photo);
		tutorServicesService.update(tutorService);
		return ResponseEntity.status(200).body("OK");
	}

	@GetMapping("api/users/tutors/{idtutor}/services/{idservice}/discount-offers")
	public ResponseEntity getTutorServiceDiscountOffers(@PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		try {
			TutorService tutorService = tutorServicesService.getById(idservice);
			return ResponseEntity.status(200).body(tutorService.getDisconutOffers());
		} catch (Exception e) {
			return ResponseEntity.ok(new ArrayList<>());
		}
	}

	@PostMapping("api/users/tutors/{idtutor}/services/{idservice}/discount-offers")
	public ResponseEntity addTutorServiceDiscountOffers(@RequestBody DiscountOffer offer,
			@PathVariable("idtutor") Integer idtutor, @PathVariable("idservice") int idservice) {
		try {
			TutorService tutorService = tutorServicesService.getById(idservice);
			tutorService.addDiscountOffer(offer);
			tutorServicesService.update(tutorService);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			System.err.println(e.getStackTrace().toString());
			return ResponseEntity.badRequest().body(e.getStackTrace());
		}
	}

	@GetMapping("api/users/tutors/{idtutor}/services/{idservice}/prices")
	public ResponseEntity getTutorServicePrice(@PathVariable("idtutor") Integer idtutor,
			@PathVariable("idservice") int idservice) {
		try {
			TutorService tutorService = tutorServicesService.getById(idservice);
			return ResponseEntity.status(200).body(tutorService.getPrices());
		} catch (Exception e) {
			return ResponseEntity.ok(new ArrayList<>());
		}
	}

	@PostMapping("api/users/tutors/{idtutor}/services/{idservice}/prices")
	public ResponseEntity addTutorServicePrice(@RequestBody ServicePrice price,
			@PathVariable("idtutor") Integer idtutor, @PathVariable("idservice") int idservice) {
		try {
			TutorService tutorService = tutorServicesService.getById(idservice);
			tutorService.addPrice(price);
			tutorServicesService.update(tutorService);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			System.err.println(e.getStackTrace().toString());
			return ResponseEntity.badRequest().body(e.getStackTrace());
		}
	}

	@DeleteMapping("api/services/{id}")
	public ResponseEntity deleteTutorService(@PathVariable("id") int id) {
		tutorServicesService.delete(id);
		return ResponseEntity.ok().build();
	}
}
