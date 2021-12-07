package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.TutorServiceAppointmentDTO;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;

@Component("tutorServiceAppointmentMapper")
public class TutorServiceAppointmentMapper extends CustomModelMapperAbstract<TutorServiceAppointment, TutorServiceAppointmentDTO> {

	@Override
	public TutorServiceAppointmentDTO convertToDto(TutorServiceAppointment entity) {
		TutorServiceAppointmentDTO dto= modelMapper.map(entity, TutorServiceAppointmentDTO.class);
		dto.setServiceId(entity.getTutorService().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public TutorServiceAppointment convertToEntity(TutorServiceAppointmentDTO dto) {
		TutorServiceAppointment entity= modelMapper.map(dto, TutorServiceAppointment.class);
		entity.setUser(new User(dto.getUserId()));
		entity.setTutorService(new TutorService(dto.getServiceId()));
		return entity;
	}
}
