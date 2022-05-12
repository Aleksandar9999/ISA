package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.AppointmentReportDTO;
import com.isa.FishingBooker.model.AppointmentReport;
@Component("AppointmentReportMapper")
public class AppointmentReportMapper extends CustomModelMapperAbstract<AppointmentReport, AppointmentReportDTO>{
	@Override
	public AppointmentReport convertToEntity(AppointmentReportDTO dto) {
		return super.convertToEntity(dto);
	}
}
