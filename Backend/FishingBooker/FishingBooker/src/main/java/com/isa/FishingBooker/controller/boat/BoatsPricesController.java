package com.isa.FishingBooker.controller.boat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatRentPrice;
import com.isa.FishingBooker.service.interfaces.BoatsService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsPricesController {
	@Autowired
	protected BoatsService boatsService;

	@GetMapping("api/boats/{idboat}/prices")
	public ResponseEntity<?> getBoatPrice(@PathVariable("idboat") int idboat) {
		Boat boat = boatsService.getById(idboat);
		return ResponseEntity.status(200).body(boat.getPrices());
	}

	@PostMapping("api/boats/{idboat}/prices")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> addBoatPrice(@RequestBody BoatRentPrice price,
			@PathVariable("idboat") int idboat) {
		Boat boat = boatsService.getById(idboat);
		boat.addPrice(price);
		boatsService.update(boat);
		return ResponseEntity.ok(price);
	}

}
