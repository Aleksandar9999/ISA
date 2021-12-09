package com.isa.FishingBooker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.exceptions.RegistrationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Role;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.model.UserTokenState;
import com.isa.FishingBooker.security.util.TokenUtils;
import com.isa.FishingBooker.service.EmailService;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthenticationController {
	@Autowired
	EmailService emailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomModelMapper<User, RegistrationDTO> userRegistrationMapper;
	@Autowired
	private CustomModelMapper<Tutor, RegistrationDTO> totorRegistrationMapper;
	@Autowired
	private CustomModelMapper<Admin, RegistrationDTO> adminRegistrationMapper;
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private TokenUtils tokenUtils;

	@PostMapping("api/login")
	public ResponseEntity<?> login(@RequestBody LoginInfoDTO userdto) {

		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		return ResponseEntity.ok(new UserTokenState(jwt, user.getRoles()));
	}

	@PostMapping("api/registration/user")
	public ResponseEntity<?> register(@RequestBody RegistrationDTO dto) {
		try {
			User user = userRegistrationMapper.convertToEntity(dto);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);// TODO: Sending email slow down response. Find out how to fix this
			return ResponseEntity.ok(user);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@PostMapping("api/registration/tutor")
	public ResponseEntity<?> registerTutor(@RequestBody RegistrationDTO dto) {
		try {
			Tutor user = totorRegistrationMapper.convertToEntity(dto, Tutor.class);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);// TODO: Sending email slow down response. Find out how to fix this
			return ResponseEntity.ok(user);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	//TODO: Provjeriti ispravnost email adrese prije registracije
	@PostMapping("api/registration/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody RegistrationDTO dto) {
		try {
			Admin user = adminRegistrationMapper.convertToEntity(dto, Admin.class);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);// TODO: Sending email slow down response. Find out how to fix this
			return ResponseEntity.ok(user);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	

}
