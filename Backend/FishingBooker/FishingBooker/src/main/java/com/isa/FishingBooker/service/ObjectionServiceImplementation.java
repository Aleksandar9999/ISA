package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.FishingBooker.exceptions.RequestHasResponseException;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;
import com.isa.FishingBooker.service.interfaces.ObjectionService;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ObjectionServiceImplementation extends CustomGenericService<Objection> implements ObjectionService {

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional
	public void addAdminResponse(int objectionId,String adminResponse,Admin admin) {
		Objection objection=this.getById(objectionId);
		if(objection.getAdminResponded()!=null) throw new RequestHasResponseException();
		objection.setAdminResponded(admin);
		objection.setResponse(adminResponse);
		//objection.setVersion(Timestamp.from(Instant.now()));
		this.update(objection);
		emailService.sendObjectionResponseNotification(objection.getUserEmail(), objection.getObjection(),adminResponse);
		emailService.sendObjectionResponseNotification(objection.getAppointment().getOwner().getEmail(),objection.getObjection(), adminResponse);

	}

}
