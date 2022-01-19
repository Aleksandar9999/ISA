package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;

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

	@DeleteMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> removeSubscriber(@PathVariable("id")int serviceId){
		tutorServicesService.removeSubscriber(serviceId,this.getLoggedinUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("api/tutor-services/{id}/subscribers/me")
	public ResponseEntity<?> isLoggedinUserSubscribe(@PathVariable("id")int id){
		return ResponseEntity.ok(this.tutorServicesService.getById(id).getSubscribers().stream().anyMatch(el->el.getId().equals(UsersController.getLoggedInUserId())));
	}
	
}
