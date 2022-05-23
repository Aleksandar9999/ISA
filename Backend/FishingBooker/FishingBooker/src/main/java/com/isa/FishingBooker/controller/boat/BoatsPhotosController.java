package com.isa.FishingBooker.controller.boat;

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
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.FirebaseStorage;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoatsPhotosController {
	@Autowired
	private FirebaseStorage firebaseStorage;
	@Autowired
	protected BoatsService boatsService;

	@GetMapping("api/boats/{idboat}/photos")
	public ResponseEntity<?> getBoatPhotos(@PathVariable("idboat") int idboat) {
		Boat boat = boatsService.getById(idboat);
		return ResponseEntity.status(200).body(boat.getPhotos());
	}

	@DeleteMapping("api/boats/{idboat}/photos/{idphoto}")
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> deleteBoatOwnerPhoto(@PathVariable("idboat") int idboat,
			@PathVariable("idphoto") int idphoto) {
		this.boatsService.deletePhoto(idboat, idphoto);
		return ResponseEntity.status(200).build();
	}

	@PostMapping(value = "api/boats/{idboat}/photos", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('BOATOWNER')")
	public ResponseEntity<?> addNewPhoto(@PathVariable("idboat") int idboat, @RequestPart("title") String title,
			@RequestPart("file") MultipartFile file) {
		String savedImageName = firebaseStorage.save(file);
		boatsService.addPhoto(idboat, new Photo(savedImageName, title));
		return ResponseEntity.status(200).build();
	}

}
