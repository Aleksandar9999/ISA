package com.isa.FishingBooker.controller;

import com.isa.FishingBooker.exceptions.DeleteRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.DeleteRequestDTO;
import com.isa.FishingBooker.dto.DeleteRequestResponseDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.interfaces.DeleteRequestService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DeleteRequestController {

	@Autowired
	private DeleteRequestService service;

	@Autowired 
	private UsersService userService;
	
	@Autowired
	private CustomModelMapper<DeleteRequest, DeleteRequestDTO> mapper;
	
	@PostMapping("api/delete-request")
	public ResponseEntity<?> createDeleteProfileRequest(@RequestBody DeleteRequestDTO dto) {
		DeleteRequest request = mapper.convertToEntity(dto);
		request.setUser(new User(getLoggedInUserId()));
		try{
			service.addNew(request);
		}catch (DeleteRequestException ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.ok(mapper.convertToDto(request));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("api/delete-request")
	public ResponseEntity<?> updateDeleteRequest(@RequestBody DeleteRequestResponseDTO dto) {
		DeleteRequest deleteRequest=mapper.convertToEntity(dto.getDeleteReguestDto());
		service.update(deleteRequest,dto.getResponse(),new Admin(UsersController.getLoggedInUserId()));
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("api/delete-request")
	public ResponseEntity<?> getDeleteProfileRequest() {
		return ResponseEntity.ok(mapper.convertToDtos(service.getAll()));
	}
	
	private Integer getLoggedInUserId() {
		return ((User) ((TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication())
				.getPrincipal()).getId();
	}
}
