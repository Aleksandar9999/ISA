package com.isa.FishingBooker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.TutorServiceAppointmentDTO;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.mapper.CustomModelMapperAbstract;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.DiscountOfferService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DiscountOffersController {
	@Autowired
	DiscountOfferService service;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/boatDiscounts")
	public ResponseEntity<?> getAllBoatDiscounts(){
		return ResponseEntity.ok(service.getAllBoatDiscountOffers());
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/resortDiscounts")
	public ResponseEntity<?> getAllResortDiscounts(){
		return ResponseEntity.ok(service.getAllResortDiscountOffers());
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/tutorDiscounts")
	public ResponseEntity<?> getAllTutorDiscounts(){
		return ResponseEntity.ok(service.getAllTutorServiceDiscountOffers());
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/reserveTutorServiceDiscount")
	public ResponseEntity<?> makeTutorServiceDiscountReservation(@RequestBody DiscountOffer discountOffer){
		return ResponseEntity.ok(service.makeTutorServiceAppointmentOfDiscount(discountOffer));
	}
	
}
