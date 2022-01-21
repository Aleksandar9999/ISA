package com.isa.FishingBooker.controller.tutorservice;

import java.util.ArrayList;
import java.util.List;

import com.isa.FishingBooker.dto.TutorServiceDTO;
import com.isa.FishingBooker.exceptions.AuthorizationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
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
public class TutorServicesController extends TutorServicesAbstractController {

	@Autowired
	private CustomModelMapper<TutorService, TutorServiceDTO> tutorServiceMapper;

	@GetMapping("api/tutor-services")
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDtos((ArrayList<TutorService>) tutorServicesService.getAll()));
	}

	@GetMapping("api/users/tutors/services") // servicesForList je ovaj endpoint pa sam ga obrisao
	public ResponseEntity<List<TutorService>> getAllServices() {
		return ResponseEntity.ok(tutorServicesService.getAll());
	}

	
	@GetMapping("api/users/tutors/services/{id}")
	public ResponseEntity<TutorService> getService(@PathVariable("id")Integer id) 
	{ return ResponseEntity.ok(tutorServicesService.getById(id)); }
	 

	@GetMapping("api/tutor-services/valid")
	public ResponseEntity<?> getAllValid() {
		return ResponseEntity.ok(tutorServiceMapper.convertToDtos(tutorServicesService.getAllValid()));
	}

	@GetMapping("api/users/tutors/{idtutor}/services")
	public ResponseEntity<?> getServicesTutorById(@PathVariable("idtutor") int id) {
		return ResponseEntity.ok(tutorServiceMapper.convertToDtos(tutorServicesService.getAllValidByTutor(id)));
	}

	@GetMapping("api/tutor-services/{idservice}")
	public ResponseEntity<?> getTutorService(@PathVariable("idservice") int idservice) {
		return ResponseEntity
				.ok(tutorServiceMapper.convertToDto((TutorService) tutorServicesService.getById(idservice)));
	}

	@PostMapping("api/tutor-services")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorService(@RequestBody TutorServiceDTO dto) {
		TutorService entity = tutorServiceMapper.convertToEntity(setLoggedinTutor(dto));
		tutorServicesService.addNew(entity);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("api/tutor-services/{idservice}")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> updateTutorService(@RequestBody TutorServiceDTO dto,
			@PathVariable("idservice") int idservice) {
		TutorService entity = tutorServiceMapper.convertToEntity(dto);
		validateTutor(entity);
		tutorServicesService.update(entity);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("api/tutor-services/{id}")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> deleteTutorService(@PathVariable("id") int id) {
		validateTutor(tutorServicesService.getById(id));
		tutorServicesService.delete(id);
		return ResponseEntity.ok().build();
	}

	private void validateTutor(TutorService entity) {
		User user = getLoggedinUser();
		if (!entity.getTutor().getId().equals(user.getId()))
			throw new AuthorizationException();
	}

	private TutorServiceDTO setLoggedinTutor(TutorServiceDTO dto) {
		User tutor = getLoggedinUser();
		dto.setTutorId(tutor.getId());
		return dto;
	}
}
