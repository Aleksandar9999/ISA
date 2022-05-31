package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.BoatAppointmentDTO;
import com.isa.FishingBooker.dto.ResortAppointmentDTO;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.User;



@Component("resortAppointmentMapper")
public class ResortAppointmentMapper extends CustomModelMapperAbstract<ResortAppointment, ResortAppointmentDTO> {

	@Override
	public ResortAppointmentDTO convertToDto(ResortAppointment entity) {
		ResortAppointmentDTO dto= modelMapper.map(entity,ResortAppointmentDTO.class);
		dto.setResortId(entity.getResort().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public ResortAppointment convertToEntity(ResortAppointmentDTO dto) {
		ResortAppointment entity= modelMapper.map(dto, ResortAppointment.class);
		entity.setUser(new User(dto.getUserId()));
		entity.setDuration(new Period(dto.getStart(),dto.getEnd()));
		entity.setResort(new Resort(dto.getResortId()));
		return entity;
	}
}
