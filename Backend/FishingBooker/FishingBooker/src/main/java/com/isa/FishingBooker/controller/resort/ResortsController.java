package com.isa.FishingBooker.controller.resort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.controller.UsersController;
import com.isa.FishingBooker.dto.BoatDTO;
import com.isa.FishingBooker.dto.FinanceDTO;
import com.isa.FishingBooker.dto.ReservationNumDTO;
import com.isa.FishingBooker.dto.ResortDTO;
import com.isa.FishingBooker.dto.TimeDTO;
import com.isa.FishingBooker.exceptions.AuthorizationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.ResortsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResortsController {
	
	@Autowired
	protected ResortsService resortsService;
	@Autowired
	private CustomModelMapper<Resort, ResortDTO> resortMapper;
	
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("api/resorts")
	public ResponseEntity<?> getAll() {
		return ResponseEntity
				.ok(resortMapper.convertToDtos((ArrayList<Resort>) resortsService.getAll()));
	}

	@GetMapping("api/users/resortowners/resorts") // za sve brodove korisnika
	public ResponseEntity<List<Resort>> getAllResorts() {
		return ResponseEntity.ok(resortsService.getAll());
	}

	
	@GetMapping("api/users/resortowners/resorts/{id}")
	public ResponseEntity<Resort> getResort(@PathVariable("id")Integer id) 
	{ return ResponseEntity.ok(resortsService.getById(id)); }
	 

	@GetMapping("api/resorts/valid")
	public ResponseEntity<?> getAllValid() {
		return ResponseEntity.ok(resortMapper.convertToDtos(resortsService.getAllValid()));
	}

	@GetMapping("api/users/resortowners/{idresortowner}/resorts") // za spisak brodova na frontu
	public ResponseEntity<?> getResortsResortOwnerById(@PathVariable("idresortowner") int id) {
		return ResponseEntity.ok(resortMapper.convertToDtos(resortsService.getAllValidByResortOwner(id)));
	}

	@GetMapping("api/resorts/{idresort}")
	public ResponseEntity<?> getResortOwnerResort(@PathVariable("idresort") int idresort) {
		return ResponseEntity
				.ok(resortMapper.convertToDto((Resort) resortsService.getById(idresort)));
	}

	@PostMapping("api/resorts")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> addResort(@RequestBody ResortDTO dto) {
		Resort entity = resortMapper.convertToEntity(setLoggedinResortOwner(dto));
		resortsService.addNew(entity);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("api/resorts/{idresort}")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> updateResort(@RequestBody ResortDTO dto,
			@PathVariable("idresort") int idresort) {
		Resort entity = resortMapper.convertToEntity(dto);
		validateResortOwner(entity);
		resortsService.updateInfo(entity);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("api/resorts/{id}")
	@PreAuthorize("hasRole('RESORTOWNER')")
	public ResponseEntity<?> deleteResort(@PathVariable("id") int id) {
		validateResortOwner(resortsService.getById(id));
		resortsService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	  @GetMapping("/api/getNumberOfReservations/resortowner/{resortownerid}")
	    public List<ReservationNumDTO> getNumberOfReservations(@PathVariable("resortownerid") int resortownerid){
	        return appointmentService.getNumberOfReservationsResort(resortownerid);
	    }

	    @PostMapping("/api/finances/resortowner")
	    public List<FinanceDTO> getFinances(@RequestBody TimeDTO timeDTO){
	        return appointmentService.getFinancesResort(timeDTO.getId(), timeDTO.getStartTime(), timeDTO.getEndTime());
	    }

	private void validateResortOwner(Resort entity) {
		if (!entity.getResortOwner().getId().equals(UsersController.getLoggedInUserId()))
			throw new AuthorizationException();
	}

	private ResortDTO setLoggedinResortOwner(ResortDTO dto) {
		dto.setResortOwnerId(UsersController.getLoggedInUserId());
		return dto;
	}

}
