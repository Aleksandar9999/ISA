package com.isa.FishingBooker.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.UserInfoDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;

	@Autowired
	private CustomModelMapper<User, UserInfoDTO> userInfoMapper;

	
	@GetMapping("api/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(usersService.getAll());
	}

/*	@PostMapping("api/users/tutors/{id}/available-periods")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addPeriod(@RequestBody Period period, @PathVariable("id") int id) {
		Tutor tutor = usersService.getTutorById(id);// TODO:FIX HARDCODE
		tutor.addPeriod(period);
		usersService.update(tutor);
		return ResponseEntity.ok(period);
	}*/

	@GetMapping("api/users/search")
	public ResponseEntity<?> getAllUsersByStatus(@RequestParam(value = "status", defaultValue = "") Status s) {
		return ResponseEntity.ok(usersService.search(s));
	}

	@PutMapping("api/users/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") int id) {
		usersService.update(user);
		return ResponseEntity.ok(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("api/admins/reset-password")
	public ResponseEntity<?> isAdminPasswordReset() {
		Admin admin=(Admin) usersService.getById(this.getLoggedInUserId());
		return ResponseEntity.ok(admin.isPasswordChanged());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("api/admins/{id}/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody User user, @PathVariable("id") int id) {
		usersService.resetAdminPassword(user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("api/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		return ResponseEntity.ok(userInfoMapper.convertToDto(usersService.getById(getUserIdFromParam(id))));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/userProfile")
	public ResponseEntity<User> getUserProfileData() {
		return ResponseEntity.ok(usersService.getUserProfileData());
	}

	@PostMapping("confirm/{id}")
	public ResponseEntity<String> confirmAccount(@PathVariable Integer id) {
		return ResponseEntity.ok(usersService.confirmAccount(id));
	}

	@GetMapping("api/users/clients")
	public ResponseEntity<?> getAllClients() {
		return ResponseEntity.ok(userInfoMapper.convertToDtos(usersService.getAllClients()));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("editUserProfile")
	public ResponseEntity<User> editUserProfile(@RequestBody User user) {
		return ResponseEntity.ok(usersService.EditUser(user));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("api/users/{id}")
	public ResponseEntity<?> deletUser(@PathVariable int id) {
		usersService.delete(id);
		return ResponseEntity.ok().build();
	}
	private Integer getUserIdFromParam(String param) {
		if (param.equals("me"))
			return getLoggedInUserId();
		else
			return Integer.parseInt(param);
	}

	private Integer getLoggedInUserId() {
		return ((User) ((TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication())
				.getPrincipal()).getId();
	}
}
