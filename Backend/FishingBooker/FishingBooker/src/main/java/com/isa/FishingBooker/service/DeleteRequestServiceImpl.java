package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;

@org.springframework.stereotype.Service
public class DeleteRequestServiceImpl extends CustomServiceAbstract<DeleteRequest> implements DeleteRequestService {

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
	public void update(DeleteRequest item) {
		super.update(item);
	}

	private void updateUserProfileStatus(int userId) {
		User user = userService.getById(userId);
		user.setStatus(Status.DELETED);
		userService.update(user);
	}

	@Override
	public void update(DeleteRequest item, String adminResponse) {
		if (item.getRequestStatus().equals(Status.ADMIN_CONFIRMED)) {
			this.updateUserProfileStatus(item.getUser().getId());
		}
		emailService.sendDeleteRequestResponseNotification(userService.getById(item.getId()), item.getRequestStatus(),
				adminResponse);
		DeleteRequest request=getById(item.getId());
		request.setRequestStatus(item.getRequestStatus());
		this.update(request);
	}

}
