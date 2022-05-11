package com.isa.FishingBooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.PointsSettings;
import com.isa.FishingBooker.service.CustomGenericService;
import com.isa.FishingBooker.service.interfaces.PointsSettingsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController(value = "api/points-settings")
public class PointsSettingsController {
	@Autowired
	private PointsSettingsService pointsSettingsService;

	@PostMapping("/api/points-settings")
	public ResponseEntity<?> save(@RequestBody PointsSettings ps) {
		pointsSettingsService.saveByAdmin(ps, (Admin) UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/api/points-settings")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(pointsSettingsService.getAll());
	}
}
