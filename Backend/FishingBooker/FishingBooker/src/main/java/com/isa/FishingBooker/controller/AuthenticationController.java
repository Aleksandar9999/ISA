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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.dto.UserConfirmationDTO;
import com.isa.FishingBooker.exceptions.RegistrationException;
import com.isa.FishingBooker.mapper.CustomModelMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Role;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.model.UserTokenState;
import com.isa.FishingBooker.security.util.TokenUtils;
import com.isa.FishingBooker.service.EmailService;
import com.isa.FishingBooker.service.UsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthenticationController {
	//TODO: Refactor
	@Autowired
	EmailService emailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomModelMapper<User, RegistrationDTO> userRegistrationMapper;
	@Autowired
	private CustomModelMapper<User, UserConfirmationDTO> userConfirmationMapper;
	@Autowired
	private UsersService usersService;
	@Autowired
	private TokenUtils tokenUtils;

	@PostMapping("api/login")
	public ResponseEntity<?> login(@RequestBody LoginInfoDTO userdto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		return ResponseEntity.ok(new UserTokenState(jwt, user.getRoles()));
	}

	@PostMapping("api/registration/user")
	public ResponseEntity<?> register(@RequestBody RegistrationDTO dto) {
		try {
			User user = userRegistrationMapper.convertToEntity(dto);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);
			return ResponseEntity.ok(user);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@PostMapping("api/registration/tutor")
	public ResponseEntity<?> registerTutor(@RequestBody RegistrationDTO dto) {
		try {
			Tutor user = (Tutor) userRegistrationMapper.convertToEntity(dto, Tutor.class);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);
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
			Admin user = (Admin) userRegistrationMapper.convertToEntity(dto, Admin.class);
			usersService.addNew(user);
			emailService.sendRegisterConfirmationMail(user);
			return ResponseEntity.ok(user);
		} catch (RegistrationException ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@PutMapping("api/users/{id}/confirmation")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> userConfirmation(@RequestBody UserConfirmationDTO dto, @PathVariable("id") int id) {
		User user = userConfirmationMapper.convertToEntity(dto);
		usersService.update(user);
		sendNotificationEmail(dto, user);
		return ResponseEntity.ok(user);
	}

	private void sendNotificationEmail(UserConfirmationDTO dto, User user) {
		if(dto.getUser().getStatus().equals(Status.REJECTED)) {
			emailService.sendRejectedConfirmationMail(user, dto.getComment());
		}else {
			emailService.sendConfirmConfirmationMail(user);
		}
	}
	
}
