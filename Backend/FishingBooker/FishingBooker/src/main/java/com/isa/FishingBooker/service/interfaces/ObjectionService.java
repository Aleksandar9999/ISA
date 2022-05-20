package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;

public interface ObjectionService extends Service<Objection> {
	public void addAdminResponse(int objectionId,String adminResponse,Admin admin);
}
