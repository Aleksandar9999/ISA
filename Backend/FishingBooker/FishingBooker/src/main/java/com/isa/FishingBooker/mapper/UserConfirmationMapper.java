package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.UserConfirmationDTO;
import com.isa.FishingBooker.model.User;
@Component("userConfirmationMapper")
public class UserConfirmationMapper extends CustomModelMapperAbstract<User, UserConfirmationDTO> {
	@Override
	public User convertToEntity(UserConfirmationDTO dto) {
		return this.modelMapper.map(dto.getUser(), User.class);
	}

}
