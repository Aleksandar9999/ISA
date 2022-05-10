package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.service.interfaces.FirebaseStorage;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TutorServicesPhotosController{

	@Autowired
	private FirebaseStorage firebaseStorage;
	@Autowired
	protected TutorServicesService tutorServicesService;

	@GetMapping("api/tutor-services/{idservice}/photos")
	public ResponseEntity<?> getTutorServicePhotos(@PathVariable("idservice") int idservice) {
		TutorService tutorService = tutorServicesService.getById(idservice);
		return ResponseEntity.status(200).body(tutorService.getPhotos());
	}

	@DeleteMapping("api/tutor-services/{idservice}/photos/{idphoto}")
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> deleteTutorPhoto(@PathVariable("idservice") int idservice,
			@PathVariable("idphoto") int idphoto) {
		this.tutorServicesService.deletePhoto(idservice, idphoto);
		return ResponseEntity.status(200).build();
	}

	@PostMapping(value = "api/tutor-services/{idservice}/photos", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('TUTOR')")
	public ResponseEntity<?> addNewPhoto(@PathVariable("idservice") int idservice, @RequestPart("title") String title,
			@RequestPart("file") MultipartFile file) {
		// TODO: Validiraj tutora
		String savedImageName = firebaseStorage.save(file);
		tutorServicesService.addPhoto(idservice, new Photo(savedImageName, title));
		return ResponseEntity.status(200).build();
	}

}
