package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.ObjectionDTO;
import com.isa.FishingBooker.model.Objection;
@Component
public class ObjectionMapper extends CustomModelMapperAbstract<Objection, ObjectionDTO> {
	
	@Override
	public Objection convertToEntity(ObjectionDTO dto) {
		Objection obj= super.convertToEntity(dto);
		if(dto.getAdminRespondedId()==0)obj.setAdminResponded(null);
		return obj;
	}

}
