package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.RevisionDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Revision;
import com.isa.FishingBooker.model.TutorRevision;
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
	
	@PostMapping("api/revision/tutor")
	public ResponseEntity<?> createNewTutorRevision(@RequestBody RevisionDTO dto){
		Revision revision=revisionModelMapper.convertToEntity(dto, TutorRevision.class);
		revisionService.addNew(revision);
		return ResponseEntity.ok(revision);
	}
	
	@PutMapping("api/revision/{id}")
	public ResponseEntity<?> updateRevision(@RequestBody RevisionDTO dto){
		Revision revision=revisionModelMapper.convertToEntity(dto, TutorRevision.class);
		revisionService.update(revision);
		return ResponseEntity.ok(revision);
	}
	
	@GetMapping("api/revision/boatAppointmentRate/{id}")
	public ResponseEntity<Integer> getRevisionsForBoatAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getBoatAppointmentRevisionsRate(id));
	}
	
	@GetMapping("api/revision/resortAppointmentRate/{id}")
	public ResponseEntity<Integer> getRevisionsForResortAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getResortAppointmentRevisionsRate(id));
	}
	
	@GetMapping("api/revision/tutorServiceAppointmentRate/{id}")
	public ResponseEntity<Integer> getRevisionsForTutorServiceAppointment(@PathVariable("id") Integer id){
		return ResponseEntity.ok(revisionService.getTutorServiceAppointmentRevisionsRate(id));
	}
	
}
