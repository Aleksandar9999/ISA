package com.isa.FishingBooker.controller.resort;

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
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.ResortRentPrice;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.ResortsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortsPricesController {

	@Autowired
	protected ResortsService resortsService;
	
	@GetMapping("api/resorts/{idresort}/prices")
	public ResponseEntity<?> getBoatPrice(@PathVariable("idresort") int idresort) {
		Resort resort = resortsService.getById(idresort);
		return ResponseEntity.status(200).body(resort.getPrices());
	}

	@PostMapping("api/resorts/{idresort}/prices")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> addResortPrice(@RequestBody ResortRentPrice price,
			@PathVariable("idresort") int idresort) {
		Resort resort = resortsService.getById(idresort);
		resort.addPrice(price);
		resortsService.update(resort);
		return ResponseEntity.ok(price);
	}
}
