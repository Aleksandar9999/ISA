package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.isa.FishingBooker.exceptions.RequestHasResponseException;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.service.interfaces.DeleteRequestService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@org.springframework.stereotype.Service
public class DeleteRequestServiceImpl extends CustomGenericService<DeleteRequest> implements DeleteRequestService {

	@Autowired
	private UsersService userService;
	@Autowired
	private EmailService emailService;

	@Override
	public void addNew(DeleteRequest item) {
		item.setRequestStatus(Status.PENDING);
		super.addNew(item);
	}

	@Override
	@Transactional
	public void update(DeleteRequest item, String adminResponse, Admin admin) {
		DeleteRequest dbRequest = getById(item.getId());
		if(!dbRequest.getRequestStatus().equals(Status.PENDING)) throw new RequestHasResponseException();
		if (item.getRequestStatus().equals(Status.ADMIN_CONFIRMED))
			this.updateUserProfileStatus(item.getUser().getId());
		dbRequest.setRequestStatus(item.getRequestStatus());
		dbRequest.setAdminResponded(admin);
		this.update(dbRequest);
		emailService.sendDeleteRequestResponseNotification(userService.getById(item.getId()), item.getRequestStatus(),
				adminResponse);
	}

	private void updateUserProfileStatus(int userId) {
		User user = userService.getById(userId);
		user.setStatus(Status.DELETED);
		userService.update(user);
	}
}
