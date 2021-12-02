package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.Status;

@org.springframework.stereotype.Service
public class DeleteRequestServiceImpl extends CustomServiceAbstract<DeleteRequest> implements DeleteRequestService {

	@Override
	public void addNew(DeleteRequest item) {
		item.setRequestStatus(Status.PENDING);
		super.addNew(item);
	}

}
