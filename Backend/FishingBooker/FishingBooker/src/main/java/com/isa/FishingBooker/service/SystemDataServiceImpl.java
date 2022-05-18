package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.SystemData;
import com.isa.FishingBooker.repository.SystemDataRepository;
import com.isa.FishingBooker.service.interfaces.SystemDataService;

@Service
public class SystemDataServiceImpl extends CustomGenericService<SystemData> implements SystemDataService {
	@Transactional
	@Override
	public void addNew(SystemData item) {
       finishCurrentlyActiveData();
       item.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
	   super.addNew(item);
	}

	private void finishCurrentlyActiveData() {
		SystemData currentlyActive = this.findCurrentlyActive();
		   currentlyActive.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
		   this.update(currentlyActive);
	}

	@Override
	public SystemData findCurrentlyActive() {
		return ((SystemDataRepository)this.repository).findCurrentlyActive();
	}
}
