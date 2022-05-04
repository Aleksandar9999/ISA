package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.DeleteRequest;

public interface DeleteRequestService extends Service<DeleteRequest> {
	public void update(DeleteRequest item,String adminResponse,Admin admin);
}
