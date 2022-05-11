package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.PointsSettings;
import com.isa.FishingBooker.model.User;

public interface PointsSettingsService extends Service<PointsSettings>{
	public void saveByAdmin(PointsSettings settings, Admin admin);
	public PointsSettings findActive();
	public void updateClientPoints(User user);
	public void updateServiceOwnerPoints(User user);
}
