package com.isa.FishingBooker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.service.ResortsService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortController {
	@Autowired
	private ResortsService resortsService;
	
	@GetMapping("/resorts")
	public ResponseEntity<ArrayList<Resort>> getAll() {
		return ResponseEntity.ok((ArrayList<Resort>) resortsService.getAll());
	}
	
	@GetMapping("resorts/{id}")
	public ResponseEntity<Resort> getByID(@PathVariable Integer id){
		return ResponseEntity.ok(resortsService.getById(id));
	}
	
}
