package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesSubscribersController extends TutorServicesAbstractController {
	@GetMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> getAllSubscribers(@PathVariable("id")int id){
		return ResponseEntity.ok(this.tutorServicesService.getById(id).getSubscribers());
	}
	
	@PostMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> addNewSubscriber(@PathVariable("id")int serviceId){
		tutorServicesService.addNewSubscriber(serviceId,this.getLoggedinUser());
		return ResponseEntity.ok().build();
	}
}
