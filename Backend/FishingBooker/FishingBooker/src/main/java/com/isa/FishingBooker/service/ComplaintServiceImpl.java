package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.complaint.Complaint;
@Service
public class ComplaintServiceImpl extends CustomServiceAbstract<Complaint> implements ComplaintService{

	@Autowired
	private EmailService emailService;
	@Autowired 
	private UsersService userService;
	@Override
	public void addNew(Complaint item) {
		item.setAdminResponse("");
		super.addNew(item);
	}

	@Override
	public void update(Complaint item) {
		super.update(item);
		sendEmailNotifications(item);
	}

	private void sendEmailNotifications(Complaint item) {
		item.setAppellant(userService.getById(item.getAppellantId()));
		item.setAppellee((userService.getById(item.getAppelleeId())));
		String subject="Complaint answer";
		String text=String.format("User complaint:%s\nAdmin answer:%s", item.getComplaintText(),item.getAdminResponse());
		emailService.sendCustomEmail(item.getAppelleeEmail(), subject, item.getAdminResponse());
		emailService.sendCustomEmail(item.getAppellantEmail(), subject, item.getAdminResponse());	
	}

}
