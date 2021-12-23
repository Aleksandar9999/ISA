package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.model.User;

@Component("registrationMapper")
public class RegistrationMapper<T extends User> extends CustomModelMapperAbstract<T, RegistrationDTO>{
	@Override
	public T convertToEntity(RegistrationDTO dto, Class<? extends T> retClass) {
		T entity = modelMapper.map(dto, retClass);
		entity.setRolesNames();
		return entity;
	}
}
