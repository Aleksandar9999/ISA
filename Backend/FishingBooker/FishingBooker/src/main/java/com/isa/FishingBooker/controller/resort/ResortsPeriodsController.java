package com.isa.FishingBooker.controller.resort;

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

import com.isa.FishingBooker.mapper.calendar.DiscountOfferCalendarMapper;
import com.isa.FishingBooker.mapper.calendar.StandardPeriodCalendarMapper;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.ResortsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortsPeriodsController {


	@Autowired
	private DiscountOfferCalendarMapper discountOfferCalendarMapper;
	@Autowired
	private StandardPeriodCalendarMapper standardPeriodCalendarMapper;
	@Autowired
	protected ResortsService resortsService;
	
	@GetMapping("api/resorts/{id}/all-standard-periods")
	public ResponseEntity<?> getAllResortsPeriods(@PathVariable("id") int idresort) {
		return ResponseEntity.status(200).body(resortsService.getAllAvailablePeriodsByResort(idresort));
	}
	
	@PreAuthorize("hasRole('RESORTOWNER')")
	@PostMapping("api/resorts/{id}/standard-periods")
	public ResponseEntity<?> addResortStandardPeriod(@RequestBody Period period,
			@PathVariable("id") int idresort) {
		//dodati proveru da li dodaje bas resortowner od tog broda
		resortsService.addNewStandardPeriod(idresort, period);
		return ResponseEntity.ok(period);
	}
	
	@GetMapping("api/resorts/{idresort}/standard-periods")
	public ResponseEntity<?> getResortStandardPeriod(@PathVariable("idresort") int idresort) {
		Resort resort = resortsService.getById(idresort);
		return ResponseEntity.status(200).body(resort.getResortAvailable());
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/resorts/standard-periods")
	public ResponseEntity<?> getAllResortsStandardPeriod(
			@RequestParam(name = "start", defaultValue = "") String start,
			@RequestParam(name = "duration", defaultValue = "") int duration) {
		return ResponseEntity.status(200).body(resortsService.getAllResortsAvailablePeriods(
				new Timestamp(Date.valueOf(start).getTime()), duration));
	}

	@GetMapping("api/resorts/{idresort}/discount-offers")
	public ResponseEntity<?> getResortDiscountOffers(@PathVariable("idresort") int idresort) {
		Resort resort = resortsService.getById(idresort);
		return ResponseEntity.status(200).body(resort.getDiscountOffers());
	}

	@PostMapping("api/resorts/{idresort}/discount-offers")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> addResortDiscountOffers(@RequestBody DiscountOffer offer,
			@PathVariable("idresort") int idresort) {
		// validacija za boatownera
		resortsService.addNewDiscountOffer(idresort, offer);
		return ResponseEntity.ok(offer);
	}
	
	@PreAuthorize("hasRole('RESORTOWNER')")
	@GetMapping("api/resort/{idresort}/discount-offers")
	public ResponseEntity<?> getAllResortsDiscountOffers(
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate,
			@PathVariable("idresort") int idresort) {
		List<DiscountOffer> allDiscountOffers = resortsService
				.getAllDiscountOffers(idresort);
		if (!(startDate.isEmpty() && endDate.isEmpty()))
			return ResponseEntity.status(200).body(discountOfferCalendarMapper.convertToDtos(allDiscountOffers,
					LocalDate.parse(startDate), LocalDate.parse(endDate)));
		return ResponseEntity.ok(allDiscountOffers);
	}

	@PreAuthorize("hasRole('RESORTOWNER')")
	@GetMapping("api/resort/{idresort}/standard-periods")
	public ResponseEntity<?> getAllResortStandardPeriod(
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate,
			@PathVariable("idresort") int idresort) {
		List<Period> allAvailablePeriodsByResort = resortsService
				.getAllAvailablePeriodsByResort(idresort);
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			return ResponseEntity.status(200).body(standardPeriodCalendarMapper
					.convertToDtos(allAvailablePeriodsByResort, LocalDate.parse(startDate), LocalDate.parse(endDate)));
		}
		return ResponseEntity.ok(allAvailablePeriodsByResort);
	}
	
	

	
}
