package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.UserInfoDTO;
import com.isa.FishingBooker.model.User;

@Component("userInfoMapper")
public class UserInfoMapper extends CustomModelMapperAbstract<User, UserInfoDTO> {

	@Override
	public UserInfoDTO convertToDto(User entity) {
		return modelMapper.map(entity, UserInfoDTO.class);
	}

	@Override
	public User convertToEntity(UserInfoDTO dto) {
		return modelMapper.map(dto, User.class);
	}

}
