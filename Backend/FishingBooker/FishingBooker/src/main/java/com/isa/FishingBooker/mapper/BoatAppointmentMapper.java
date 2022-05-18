package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.BoatAppointmentDTO;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.User;


@Component("boatAppointmentMapper")
public class BoatAppointmentMapper extends CustomModelMapperAbstract<BoatAppointment, BoatAppointmentDTO>{

	@Override
	public BoatAppointmentDTO convertToDto(BoatAppointment entity) {
		BoatAppointmentDTO dto= modelMapper.map(entity,BoatAppointmentDTO.class);
		dto.setBoatId(entity.getBoat().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public BoatAppointment convertToEntity(BoatAppointmentDTO dto) {
		BoatAppointment entity= modelMapper.map(dto, BoatAppointment.class);
		entity.setUser(new User(dto.getUserId()));
		entity.setDuration(new Period(dto.getStart(),dto.getEnd()));
		entity.setBoat(new Boat(dto.getBoatId()));
		return entity;
	}
}
