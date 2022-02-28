package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.SystemData;
import com.isa.FishingBooker.repository.SystemDataRepository;

@Service
public class SystemDataServiceImpl extends CustomServiceAbstract<SystemData> implements SystemDataService {
	@Transactional
	@Override
	public void addNew(SystemData item) {
       finishCurrentlyActiveData();
       item.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
	   super.addNew(item);
	}

	private void finishCurrentlyActiveData() {
		SystemData currentlyActive = ((SystemDataRepository)this.repository).findCurrentlyActive();
		   currentlyActive.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
		   this.update(currentlyActive);
	}
}
