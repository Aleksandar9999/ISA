package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.DeleteRequest;

public interface DeleteRequestService extends Service<DeleteRequest> {
	public void update(DeleteRequest item,String adminResponse);
}
