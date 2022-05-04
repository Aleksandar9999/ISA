package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.SystemData;
import com.isa.FishingBooker.model.UserCategory;
import com.isa.FishingBooker.service.CustomGenericService;
import com.isa.FishingBooker.service.interfaces.SystemDataService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SystemDataController {
	
	@Autowired
	private SystemDataService systemDataService;
	@Autowired
	private CustomGenericService<UserCategory> userCategoryService;
	
	@GetMapping("api/system-data/procentage")
	public ResponseEntity<?> getSystemData() {
		return ResponseEntity.ok(systemDataService.getAll());
	}
	
	@PostMapping("api/system-data/procentage")
	public ResponseEntity<?> addSystemData(@RequestBody SystemData systemData) {
		systemDataService.addNew(systemData);
		return ResponseEntity.ok(systemData);
	}
	
	@GetMapping("api/system-data/user-categories")
	public ResponseEntity<?> getUserCategories() {
		return ResponseEntity.ok(userCategoryService.getAll());
	}
	
	@PutMapping("api/system-data/user-categories")
	public ResponseEntity<?> updateCategory(@RequestBody UserCategory category) {
		userCategoryService.update(category);
		return ResponseEntity.ok().build();
	}
	
}
