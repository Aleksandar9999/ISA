package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.UserCategorySettings;
import com.isa.FishingBooker.service.interfaces.UserCategorySettingsService;
@Service
public class UserCategorySettingsServiceImpl extends CustomGenericService<UserCategorySettings>
		implements UserCategorySettingsService {

	@Override
	public double findOwnerRevenueProcentage(double points) {
		return this.findByUserPoints(points).getRevenueProcentage();
	}

	@Override
	public double findDiscountProcentage(double points) {
		return this.findByUserPoints(points).getDiscountProcentage();
	}

	@Override
	public UserCategorySettings findByUserPoints(double points) {
		List<UserCategorySettings> settings = this.getAll();
		UserCategorySettings retSettings = null;
		for (UserCategorySettings userCategorySettings : settings) {
			if (userCategorySettings.getPointsMargine() <= points)
				retSettings = userCategorySettings;
		}
		return retSettings;
	}

}
