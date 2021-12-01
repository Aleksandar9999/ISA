package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.User;

@Component("registrationDtoMapper")
public class RegistrationDTOMapper extends CustomModelMapperAbstract<User, RegistrationDTO>{

	@Override
	public RegistrationDTO convertToDto(User entity) {
		return modelMapper.map(entity, RegistrationDTO.class);
	}

	@Override
	public User convertToEntity(RegistrationDTO dto) {
		return modelMapper.map(dto, User.class);
	}
	
}
