package com.isa.FishingBooker.controller.boat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.mapper.calendar.DiscountOfferCalendarMapper;
import com.isa.FishingBooker.mapper.calendar.StandardPeriodCalendarMapper;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsPeriodsController {
	
	@Autowired
	private DiscountOfferCalendarMapper discountOfferCalendarMapper;
	@Autowired
	private StandardPeriodCalendarMapper standardPeriodCalendarMapper;
	@Autowired
	protected BoatsService boatsService;
	
	@GetMapping("api/boats/{id}/all-standard-periods")
	public ResponseEntity<?> getAllBoatsPeriods(@PathVariable("id") int idboat) {
		return ResponseEntity.status(200).body(boatsService.getAllAvailablePeriodsByBoat(idboat));
	}
	
	@PreAuthorize("hasRole('BOATOWNER')")
	@PostMapping("api/boats/{id}/standard-periods")
	public ResponseEntity<?> addBoatStandardPeriod(@RequestBody Period period,
			@PathVariable("id") int idboat) {
		//dodati proveru da li dodaje bas boatowner od tog broda
		boatsService.addNewStandardPeriod(idboat, period);
		return ResponseEntity.ok(period);
	}
	
	@GetMapping("api/boats/{idboat}/standard-periods")
	public ResponseEntity<?> getBoatStandardPeriod(@PathVariable("idboat") int idboat) {
		Boat boat = boatsService.getById(idboat);
		return ResponseEntity.status(200).body(boat.getAvailable());
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/boats/standard-periods")
	public ResponseEntity<?> getAllBoatsStandardPeriod(
			@RequestParam(name = "start", defaultValue = "") String start,
			@RequestParam(name = "duration", defaultValue = "") int duration,
			@RequestParam(name = "number-of-guests", defaultValue = "") int maxPersons) {
		return ResponseEntity.status(200).body(boatsService.getAllBoatsAvailablePeriods(
				new Timestamp(Date.valueOf(start).getTime()), duration, maxPersons));
	}

	@GetMapping("api/boats/{idboat}/discount-offers")
	public ResponseEntity<?> getBoatDiscountOffers(@PathVariable("idboat") int idboat) {
		Boat boat = boatsService.getById(idboat);
		return ResponseEntity.status(200).body(boat.getDisconutOffers());
	}

	@PostMapping("api/boats/{idboat}/discount-offers")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> addBoatDiscountOffers(@RequestBody DiscountOffer offer,
			@PathVariable("idboat") int idboat) {
		// validacija za boatownera
		boatsService.addNewDiscountOffer(idboat, offer);
		return ResponseEntity.ok(offer);
	}
	
	

	





}
