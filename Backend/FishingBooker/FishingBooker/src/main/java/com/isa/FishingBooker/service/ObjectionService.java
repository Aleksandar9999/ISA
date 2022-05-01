package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Objection;

public interface ObjectionService extends Service<Objection> {
	public void addAdminResponse(Objection o,String adimnResponse,Admin admin);
}
