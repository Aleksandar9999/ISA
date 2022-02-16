package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.RevisionDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.model.revision.BoatAppointmentRevision;
import com.isa.FishingBooker.model.revision.ResortAppointmentRevision;
import com.isa.FishingBooker.model.revision.Revision;
import com.isa.FishingBooker.model.revision.TutorRevision;
import com.isa.FishingBooker.model.revision.TutorServiceAppointmentRevision;
import com.isa.FishingBooker.service.RevisionService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RevisionController {

	@Autowired
	private RevisionService revisionService;
	
	@Autowired
	private CustomModelMapper<Revision, RevisionDTO> revisionModelMapper;
	
	@GetMapping("api/revision")
	public ResponseEntity<?> getAllRevisions(){
		return ResponseEntity.ok(revisionModelMapper.convertToDtos(revisionService.getAll()));
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("api/revision/tutor")
	public ResponseEntity<?> createNewTutorRevision(@RequestBody RevisionDTO dto){
		Revision revision=revisionModelMapper.convertToEntity(dto, TutorRevision.class);
		revision.setCreator(new User(UsersController.getLoggedInUserId()));
		revisionService.makeTutorRevision(revision);
		return ResponseEntity.ok(revision);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("api/revision/{id}")
	public ResponseEntity<?> updateRevision(@RequestBody RevisionDTO dto){
		Revision revision;
		try {
			revision = revisionModelMapper.convertToEntity(dto, (Class<? extends Revision>) Class.forName(dto.getClassName()));
			revisionService.updateRevisionStatus(revision,UsersController.getLoggedInUserId());
			return ResponseEntity.ok(revision);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Unrecognized class.");
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/revision/boatAppointmentRate/{id}")
	public ResponseEntity<?> getRevisionsForBoatAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getBoatAppointmentRevisionsRate(id));
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/revision/resortAppointmentRate/{id}")
	public ResponseEntity<?> getRevisionsForResortAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getResortAppointmentRevisionsRate(id));
	}
	
	@GetMapping("api/revision/tutor-service/{id}/rate")
	public ResponseEntity<?> getRevisionsForTutorServiceAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getTutorServiceAppointmentRevisionsRate(id));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("api/revision/makeTutorServiceRevision")
	public ResponseEntity<?> makeTutorServiceRevision(@RequestBody TutorServiceAppointmentRevision revision){
		revision.setCreator(new User(UsersController.getLoggedInUserId()));
		return ResponseEntity.ok(revisionService.makeTutorServiceRevision(revision));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("api/revision/makeBoatRevision")
	public ResponseEntity<?> makeBoatRevision(@RequestBody BoatAppointmentRevision revision){
		revision.setCreator(new User(UsersController.getLoggedInUserId()));
		
		return ResponseEntity.ok(revisionService.makeBoatRevision(revision));
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("api/revision/makeResortRevision")
	public ResponseEntity<?> makeResortRevision(@RequestBody ResortAppointmentRevision revision){
		revision.setCreator(new User(UsersController.getLoggedInUserId()));
		return ResponseEntity.ok(revisionService.makeResortRevision(revision));
	}
	
}
