package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.ComplaintDTO;
import com.isa.FishingBooker.model.complaint.Complaint;
@Component
public class ComplaintMapper<E extends Complaint> extends CustomModelMapperAbstract<E, ComplaintDTO> {
	
	@Override
	public ComplaintDTO convertToDto(E entity) {
		ComplaintDTO dto= super.convertToDto(entity);
		dto.setAppelleeServiceId(entity.getAppelleServiceId());
		dto.setType(entity.getClass().toString());
		return dto;
	}
}
