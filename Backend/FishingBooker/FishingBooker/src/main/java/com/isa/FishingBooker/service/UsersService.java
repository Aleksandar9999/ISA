package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.User;

public interface UsersService extends Service<User> {
	
	public String Login(User user);
	public boolean Register(User user);
}
