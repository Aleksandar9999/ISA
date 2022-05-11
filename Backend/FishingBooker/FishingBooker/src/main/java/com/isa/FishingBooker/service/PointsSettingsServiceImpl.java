package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.PointsSettings;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.PointsSettingsRepository;
import com.isa.FishingBooker.service.interfaces.PointsSettingsService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@Service
public class PointsSettingsServiceImpl extends CustomGenericService<PointsSettings> implements PointsSettingsService{
	
	@Autowired
	private UsersService usersService;
	
	@Transactional
	@Override
	public void addNew(PointsSettings item) {
		PointsSettings activePointsSettings = ((PointsSettingsRepository)repository).findActiveSettings();
		activePointsSettings.setEndDate(Timestamp.from(Instant.now()));
		super.update(item);
		item.setStartDate(Timestamp.from(Instant.now()));
		item.setId(null);
		super.addNew(item);
	}
	
	@Override
	public void saveByAdmin(PointsSettings settings, Admin admin) {
		settings.setCreator(admin);
		this.addNew(settings);
	}

	@Override
	public PointsSettings findActive() {
		return ((PointsSettingsRepository)repository).findActiveSettings();
	}

	
	@Transactional
	@Override
	public void updateClientPoints(User user) {
		PointsSettings activeSettings=this.findActive();
		usersService.update(user.addPoints(activeSettings.getPointsForClients()));
	}
	
	@Transactional
	@Override
	public void updateServiceOwnerPoints(User user) {
		PointsSettings activeSettings=this.findActive();
		usersService.update(user.addPoints(activeSettings.getPointsForOwners()));	
	}
	
}
