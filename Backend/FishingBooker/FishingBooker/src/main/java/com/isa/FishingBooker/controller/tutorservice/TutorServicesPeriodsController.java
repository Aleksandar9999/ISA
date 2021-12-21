package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.exceptions.AuthorizationException;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.TutorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesPeriodsController extends TutorServicesAbstractController {
	@GetMapping("api/users/tutors/{id}/standard-periods")
	public ResponseEntity<?> getPeriodsAllTutorServices(@PathVariable("id") int idtutor) {
		tutorServicesService.getAllAvailablePeriodsByTutor(idtutor);
		return ResponseEntity.status(200).body(tutorServicesService.getAllAvailablePeriodsByTutor(idtutor));
	}

	@PostMapping("api/tutor-services/{idservice}/standard-periods")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addTutorServiceStandardPeriod(@RequestBody Period period,
			@PathVariable("idservice") int idservice) {
		tutorServicesService.addNewStandardPeriod(idservice, period);
		return ResponseEntity.ok(period);
	}

	@GetMapping("api/tutor-services/{idservice}/standard-periods")
	public ResponseEntity<?> getTutorServiceStandardPeriod(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getStandardPeriods());
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
		// validateTutor(tutorService); TODO: VALIDACIJA
		tutorServicesService.addNewDiscountOffer(idservice, offer);
		return ResponseEntity.ok(offer);
	}
}
