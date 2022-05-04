package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.FishingBooker.exceptions.RequestHasResponseException;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;
import com.isa.FishingBooker.service.interfaces.ObjectionService;

@Service
public class ObjectionServiceImplementation extends CustomGenericService<Objection> implements ObjectionService {

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional
	public void addAdminResponse(Objection o,String adimnResponse,Admin admin) {
		Objection objection=this.getById(o.getId());
		if(objection.getAdminResponded()!=null) throw new RequestHasResponseException();
		objection.setAdminResponded(admin);
		objection.setResponse(adimnResponse);
		emailService.sendObjectionResponseNotification(objection.getUserEmail(), adimnResponse);
		emailService.sendObjectionResponseNotification(objection.getAppointment().getOwner().getEmail(), adimnResponse);
		this.update(objection);
	}

}
