package com.isa.FishingBooker.controller.tutorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;

public class TutorServicesAbstractController {
	@Autowired
	protected TutorServicesService tutorServicesService;
	protected User getLoggedinUser() {
		TokenBasedAuthentication auth = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User tutor = (User) auth.getPrincipal();
		return tutor;
	}

}
