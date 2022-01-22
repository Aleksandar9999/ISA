package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;

@Service
public class ObjectionServiceImplementation extends CustomServiceAbstract<Objection> implements ObjectionService {

	@Autowired
	private EmailService emailService;

	@Override
	public void sendAdminResponsEmails(Objection o,String adimnResponse,Admin admin) {
		Objection objection=this.getById(o.getId());
		objection.setAdminResponded(admin);
		emailService.sendObjectionResponseNotification(objection.getUserEmail(), adimnResponse);
		emailService.sendObjectionResponseNotification(objection.getAppointment().getOwner().getEmail(), adimnResponse);
		this.update(objection);
	}

}
