package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.ComplaintDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.complaint.Complaint;
import com.isa.FishingBooker.model.complaint.TutorComplaint;
import com.isa.FishingBooker.service.ComplaintService;

@RestController
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private CustomModelMapper<Complaint, ComplaintDTO> complaintMapper;

	@GetMapping("api/complaint")
	public ResponseEntity getAll() {return ResponseEntity.ok(complaintMapper.convertToDtos(complaintService.getAll()));}
	
	@PostMapping("api/complaint/tutor")
	public ResponseEntity<?> createTutorComplaint( @RequestBody ComplaintDTO dto) {
		Complaint complaint=complaintMapper.convertToEntity(dto,TutorComplaint.class);
		complaintService.addNew(complaint);
		return ResponseEntity.ok(complaint);
	}
	
	@PutMapping("api/complaint/tutor/{id}")
	public ResponseEntity<?> createResponse(@PathVariable("id") int id, @RequestBody ComplaintDTO dto) {
		Complaint complaint=complaintMapper.convertToEntity(dto,TutorComplaint.class);
		complaintService.update(complaint);
		return ResponseEntity.ok().build();
	}
}
