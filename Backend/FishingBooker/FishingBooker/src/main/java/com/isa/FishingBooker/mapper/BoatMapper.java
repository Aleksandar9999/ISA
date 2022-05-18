package com.isa.FishingBooker.mapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.BoatDTO;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatOwner;


@Component("boatMapper")
public class BoatMapper extends CustomModelMapperAbstract<Boat, BoatDTO>{

	@Override
	public BoatDTO convertToDto(Boat entity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BoatDTO dto = modelMapper.map(entity, BoatDTO.class);
		//dto.setTutorId(entity.getTutor().getId());
		return dto;
	}

	@Override
	public Boat convertToEntity(BoatDTO dto) {
		Boat boat=modelMapper.map(dto, Boat.class);
		boat.setBoatOwner(new BoatOwner(dto.getBoatOwnerId()));
		return boat;
	}
}
