package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.DeleteRequestDTO;
import com.isa.FishingBooker.model.DeleteRequest;
import com.isa.FishingBooker.model.User;

@Component("deleteRequestMapper")
public class DeleteRequestMapper extends CustomModelMapperAbstract<DeleteRequest, DeleteRequestDTO> {

	@Override
	public DeleteRequestDTO convertToDto(DeleteRequest entity) {
		DeleteRequestDTO req=modelMapper.map(entity, DeleteRequestDTO.class);
		req.setUserId(entity.getId());
		return req;
	}
}
