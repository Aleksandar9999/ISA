package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.UserCategorySettings;
import com.isa.FishingBooker.service.interfaces.UserCategorySettingsService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserCategorySettingsController {
	@Autowired
	private UserCategorySettingsService userCategoryService;
	
	@Autowired
	private UsersService usersService;

	@GetMapping("api/system-data/user-categories")
	public ResponseEntity<?> getUserCategories() {
		return ResponseEntity.ok(userCategoryService.getAll());
	}

	@GetMapping("api/system-data/user-categories/me")
	public ResponseEntity<?> getUserCategoryByLoggedinUser() {
		return ResponseEntity.ok(userCategoryService.findByUserPoints(usersService.getById(UsersController.getLoggedInUserId()).getPoints()));
	}
	
	@PutMapping("api/system-data/user-categories")
	public ResponseEntity<?> updateCategory(@RequestBody UserCategorySettings category) {
		userCategoryService.update(category);
		return ResponseEntity.ok().build();
	}

}
