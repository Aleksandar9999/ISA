package com.isa.FishingBooker.controller.tutorservice;

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
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesPeriodsController {
	@Autowired
	private DiscountOfferCalendarMapper discountOfferCalendarMapper;
	@Autowired
	private StandardPeriodCalendarMapper standardPeriodCalendarMapper;
	@Autowired
	protected TutorServicesService tutorServicesService;

	@GetMapping("api/users/tutors/{id}/standard-periods")
	public ResponseEntity<?> getPeriodsAllTutorServices(@PathVariable("id") int idtutor) {
		return ResponseEntity.status(200).body(tutorServicesService.getAllAvailablePeriodsByTutor(idtutor));
	}

	@PreAuthorize("hasRole('TUTOR')")
	@PostMapping("api/users/tutors/standard-periods")
	public ResponseEntity<?> addTutortandardPeriod(@RequestBody Period period) {
		tutorServicesService.addNewStandardPeriod(UsersController.getLoggedInUserId(), period);
		return ResponseEntity.ok(period);
	}

	@GetMapping("api/tutor-services/{idservice}/standard-periods")
	public ResponseEntity<?> getTutorServiceStandardPeriod(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getTutor().getAvailable());
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("api/tutor-services/standard-periods")
	public ResponseEntity<?> getAllTutorServiceStandardPeriod(
			@RequestParam(name = "start", defaultValue = "") String start,
			@RequestParam(name = "duration", defaultValue = "") int duration,
			@RequestParam(name = "number-of-guests", defaultValue = "") int maxPersons) {
		return ResponseEntity.status(200).body(tutorServicesService.getAllTutorServicesAvailablePeriods(
				new Timestamp(Date.valueOf(start).getTime()), duration, maxPersons));
	}

	@GetMapping("api/tutor-services/{idservice}/discount-offers")
	public ResponseEntity<?> getTutorServiceDiscountOffers(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getDisconutOffers());
	}

	@PostMapping("api/tutor-services/{idservice}/discount-offers")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorServiceDiscountOffers(@RequestBody DiscountOffer offer,
			@PathVariable("idservice") int idservice) {
		// validateTutor(tutorService); //TODO: VALIDACIJA
		tutorServicesService.addNewDiscountOffer(idservice, offer);
		return ResponseEntity.ok(offer);
	}

	@PreAuthorize("hasRole('TUTOR')")
	@GetMapping("api/tutor-services/discount-offers")
	public ResponseEntity<?> getAllTutorDiscountOffers(
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		List<DiscountOffer> allDiscountOffers = tutorServicesService
				.getAllDiscountOffers(UsersController.getLoggedInUserId());
		if (!(startDate.isEmpty() && endDate.isEmpty()))
			return ResponseEntity.status(200).body(discountOfferCalendarMapper.convertToDtos(allDiscountOffers,
					LocalDate.parse(startDate), LocalDate.parse(endDate)));
		return ResponseEntity.ok(allDiscountOffers);
	}

	@PreAuthorize("hasRole('TUTOR')")
	@GetMapping("api/tutor-services/standard-periods/me")
	public ResponseEntity<?> getAllTutorStandardPeriod(
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name = "endDate", defaultValue = "") String endDate) {
		List<Period> allAvailablePeriodsByTutor = tutorServicesService
				.getAllAvailablePeriodsByTutor(UsersController.getLoggedInUserId());
		if (!(startDate.isEmpty() && endDate.isEmpty())) {
			return ResponseEntity.status(200).body(standardPeriodCalendarMapper
					.convertToDtos(allAvailablePeriodsByTutor, LocalDate.parse(startDate), LocalDate.parse(endDate)));
		}
		return ResponseEntity.ok(allAvailablePeriodsByTutor);
	}
}
