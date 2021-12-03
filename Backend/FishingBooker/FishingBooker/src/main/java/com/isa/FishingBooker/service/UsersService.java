package com.isa.FishingBooker.service;

import java.util.List;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;

public interface UsersService extends Service<User> {
	

	public Tutor getTutorById(int id);
	public List<User> search(Status status);

	public LoginReturnDTO Login(LoginInfoDTO user);
	public String confirmAccount(Integer id);
}
