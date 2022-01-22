package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.DeleteRequestDTO;
import com.isa.FishingBooker.model.DeleteRequest;

@Component("deleteRequestMapper")
public class DeleteRequestMapper extends CustomModelMapperAbstract<DeleteRequest, DeleteRequestDTO> {

	@Override
	public DeleteRequestDTO convertToDto(DeleteRequest entity) {
		DeleteRequestDTO req=modelMapper.map(entity, DeleteRequestDTO.class);
		req.setUserId(entity.getId());
		return req;
	}
	@Override
	public DeleteRequest convertToEntity(DeleteRequestDTO dto) {
		DeleteRequest dr= super.convertToEntity(dto);
		if(dto.getAdminRespondedId()==0)dr.setAdminResponded(null);
		return dr;
	}
}
