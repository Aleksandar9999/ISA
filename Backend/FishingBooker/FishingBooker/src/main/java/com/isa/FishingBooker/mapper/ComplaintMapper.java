package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.ComplaintDTO;
import com.isa.FishingBooker.model.complaint.Complaint;
@Component
public class ComplaintMapper<E extends Complaint> extends CustomModelMapperAbstract<E, ComplaintDTO> {
	@Override
	public E convertToEntity(ComplaintDTO dto, Class<? extends E> retClass) {
		E entitity = super.convertToEntity(dto, retClass);
		entitity.setAppellantId(dto.getAppellantId());
		entitity.setAppelleeId(dto.getAppelleeId());
		return entitity;
	}
}
