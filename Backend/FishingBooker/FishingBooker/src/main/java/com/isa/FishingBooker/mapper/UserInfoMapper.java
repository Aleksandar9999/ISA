package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.UserInfoDTO;
import com.isa.FishingBooker.model.User;

@Component("userInfoMapper")
public class UserInfoMapper extends CustomModelMapperAbstract<User, UserInfoDTO> {

	@Override
	public UserInfoDTO convertToDto(User entity) {
		UserInfoDTO dto= super.convertToDto(entity);
		dto.setClassName(entity.getClass().toString());
		dto.setShouldApprove(!entity.isEnabled());
		return dto;
	}
	
}
