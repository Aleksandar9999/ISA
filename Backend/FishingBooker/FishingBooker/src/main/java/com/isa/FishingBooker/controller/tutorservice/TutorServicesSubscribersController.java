package com.isa.FishingBooker.controller.tutorservice;

import com.isa.FishingBooker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesSubscribersController {
	@Autowired
	protected TutorServicesService tutorServicesService;

	@GetMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> getAllSubscribers(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.tutorServicesService.getById(id).getSubscribers());
	}

	@PostMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> addNewSubscriber(@PathVariable("id") int serviceId) {
		tutorServicesService.addNewSubscriber(serviceId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("api/tutor-services/{id}/subscribers")
	public ResponseEntity<?> removeSubscriber(@PathVariable("id") int serviceId) {
		tutorServicesService.removeSubscriber(serviceId, UsersController.getLoggedInUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("api/tutor-services/{id}/subscribers/me")
	public ResponseEntity<?> isLoggedinUserSubscribe(@PathVariable("id") int id) {
		Set<User> subscribers=this.tutorServicesService.getById(id).getSubscribers();
		boolean subscribed =subscribers.stream()
				.anyMatch(el -> el.getId().equals(UsersController.getLoggedInUserId()));
		return subscribed?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}

}
