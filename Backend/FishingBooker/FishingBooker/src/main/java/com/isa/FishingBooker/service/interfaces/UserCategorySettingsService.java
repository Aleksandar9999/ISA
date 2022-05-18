package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.UserCategorySettings;

public interface UserCategorySettingsService extends Service<UserCategorySettings>{
	public double findOwnerRevenueProcentage(double points);
	public double findDiscountProcentage(double points);
	public UserCategorySettings findByUserPoints(double points);
}
