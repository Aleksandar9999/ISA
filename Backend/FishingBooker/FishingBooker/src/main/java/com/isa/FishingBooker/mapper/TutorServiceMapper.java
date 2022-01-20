package com.isa.FishingBooker.mapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.TutorServiceDTO;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;

@Component("tutorServiceMapper")
public class TutorServiceMapper extends CustomModelMapperAbstract<TutorService, TutorServiceDTO> {

	@Override
	public TutorServiceDTO convertToDto(TutorService entity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TutorServiceDTO dto = modelMapper.map(entity, TutorServiceDTO.class);
		dto.setTutor(entity.getTutor().getId());
		return dto;
	}

	@Override
	public TutorService convertToEntity(TutorServiceDTO dto) {
		TutorService tutorService=modelMapper.map(dto, TutorService.class);
		tutorService.setTutor(new Tutor(dto.getTutorId()));
		return tutorService;
	}
}
