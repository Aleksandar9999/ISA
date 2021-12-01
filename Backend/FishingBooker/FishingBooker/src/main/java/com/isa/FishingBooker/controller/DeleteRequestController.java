package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.DeleteRequestDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.service.DeleteRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DeleteRequestController {

	@Autowired
	private DeleteRequestService service;

	@Autowired
	private CustomModelMapper<DeleteRequest, DeleteRequestDTO> mapper;
	@PostMapping("api/delete-request")
	public ResponseEntity createDeleteProfileRequest(@RequestBody DeleteRequestDTO dto) {
		// TODO: Bice prijavljen, uzmem ID iz JWT i napravim zahtijev DeleteRequest
		DeleteRequest request =mapper.convertToEntity(dto);
		request.setUser(new Tutor(4));// TODO: FIX HARDCODE
		service.addNew(request);
		return ResponseEntity.ok(mapper.convertToDto(request));
	}

	@GetMapping("api/delete-request")
	public ResponseEntity getDeleteProfileRequest() {
		return ResponseEntity.ok(mapper.convertToDtos(service.getAll()));
	}
}
