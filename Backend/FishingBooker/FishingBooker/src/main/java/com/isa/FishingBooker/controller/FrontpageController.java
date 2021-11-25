package com.isa.FishingBooker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.service.BoatsServiceImplementation;
import com.isa.FishingBooker.service.ResortsServiceImplementation;
import com.isa.FishingBooker.service.TutorsServiceImplementation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class FrontpageController {
	
	@Autowired
//	private BoatsServiceImplementation bService;
	private ResortsServiceImplementation rService;
	private TutorsServiceImplementation tService;
	
/*	@GetMapping("/boats")
	public List<Boat> getAllBoats(){
		return bService.getAll();
	}
*/
	@GetMapping("/resorts")
	public List<Resort> getAllResorts(){
		return rService.getAll();
	}
	
	@GetMapping("/tutors")
	public List<Tutor> getAllTutors(){
		return tService.getAll();
	}
	

}
