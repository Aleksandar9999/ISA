package com.isa.FishingBooker.controller.resort;

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

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.FirebaseStorage;
import com.isa.FishingBooker.service.interfaces.ResortsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortsPhotosController {
	@Autowired
	private FirebaseStorage firebaseStorage;
	@Autowired
	protected ResortsService resortsService;

	@GetMapping("api/resorts/{idresort}/photos")
	public ResponseEntity<?> getResortPhotos(@PathVariable("idresort") int idresort) {
		Resort resort = resortsService.getById(idresort);
		return ResponseEntity.status(200).body(resort.getPhotos());
	}

	@DeleteMapping("api/resorts/{idresort}/photos/{idphoto}")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> deleteResortOwnerPhoto(@PathVariable("idresort") int idresort,
			@PathVariable("idphoto") int idphoto) {
		this.resortsService.deletePhoto(idresort, idphoto);
		return ResponseEntity.status(200).build();
	}

	@PostMapping(value = "api/resorts/{idresort}/photos", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> addNewPhoto(@PathVariable("idresort") int idresort, @RequestPart("title") String title,
			@RequestPart("file") MultipartFile file) {
		String savedImageName = firebaseStorage.save(file);
		resortsService.addPhoto(idresort, new Photo(savedImageName, title));
		return ResponseEntity.status(200).build();
	}

}
