package com.isa.FishingBooker.mapper;

import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.User;

public class RegistrationDTOtoUserMapper {
	
	public RegistrationDTOtoUserMapper() {}
	
	public User RegistrationDTOtoUser(RegistrationDTO dto) {
		User user=new User();
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setAddress(dto.getAddress());
		user.setCity(dto.getCity());
		user.setCountry(dto.getCountry());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhoneNumber(dto.getPhoneNumber());
		return user;
	}
	
}
