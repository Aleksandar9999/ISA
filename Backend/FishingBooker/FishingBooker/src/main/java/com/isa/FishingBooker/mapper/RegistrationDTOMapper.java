package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.User;

@Component("registrationDtoMapper")
public class RegistrationDTOMapper<T extends User> extends CustomModelMapperAbstract<T, RegistrationDTO>{

	@Override
	public T convertToEntity(RegistrationDTO dto, Class<T> retClass) {
		T entity = modelMapper.map(dto, retClass);
		entity.setRolesNames();
		return entity;
	}

	@Override
	public RegistrationDTO convertToDto(T entity) {
		return modelMapper.map(entity, RegistrationDTO.class);
	}

	@Override
	public T convertToEntity(RegistrationDTO dto) {
		return this.convertToEntity(dto, (Class<T>) User.class);
	}

	

}
