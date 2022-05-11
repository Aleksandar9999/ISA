package com.isa.FishingBooker.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.isa.FishingBooker.model.Photo;

public interface FirebaseStorage {
	String save(MultipartFile file);
}
