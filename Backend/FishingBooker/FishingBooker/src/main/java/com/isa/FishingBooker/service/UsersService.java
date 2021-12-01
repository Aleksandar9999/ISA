package com.isa.FishingBooker.service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.User;

public interface UsersService extends Service<User> {
	
	public LoginReturnDTO Login(LoginInfoDTO user);
	public String Register(RegistrationDTO user);
}
