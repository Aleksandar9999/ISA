package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Revision;
import com.isa.FishingBooker.model.Status;

@org.springframework.stereotype.Service
public class RevisionServiceImpl extends CustomServiceAbstract<Revision> implements RevisionService {
	@Override
	public void addNew(Revision item) {
		item.setStatus(Status.PENDING);
		super.addNew(item);
	}
}
