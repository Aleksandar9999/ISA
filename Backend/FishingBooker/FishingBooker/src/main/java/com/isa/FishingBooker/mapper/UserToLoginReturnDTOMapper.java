package com.isa.FishingBooker.mapper;

import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.model.User;

public class UserToLoginReturnDTOMapper {
	
	public UserToLoginReturnDTOMapper() {}
	
	public LoginReturnDTO mapUserToLoginReturnDTO(User u, LoginReturnDTO dto) {
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setStatus(u.getStatus());
		return dto;
	}
}
