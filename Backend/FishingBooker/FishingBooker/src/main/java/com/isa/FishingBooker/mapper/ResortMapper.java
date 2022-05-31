package com.isa.FishingBooker.mapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.BoatDTO;
import com.isa.FishingBooker.dto.ResortDTO;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.ResortOwner;

@Component("resortMapper")
public class ResortMapper  extends CustomModelMapperAbstract<Resort, ResortDTO> {

	@Override
	public ResortDTO convertToDto(Resort entity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ResortDTO dto = modelMapper.map(entity, ResortDTO.class);
		//dto.setTutorId(entity.getTutor().getId());
		return dto;
	}

	@Override
	public Resort convertToEntity(ResortDTO dto) {
		Resort resort=modelMapper.map(dto, Resort.class);
		resort.setResortOwner(new ResortOwner(dto.getResortOwnerId()));
		return resort;
	}
}
