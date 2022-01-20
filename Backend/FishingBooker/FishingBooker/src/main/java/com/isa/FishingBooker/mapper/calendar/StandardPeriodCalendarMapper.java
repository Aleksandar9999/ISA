package com.isa.FishingBooker.mapper.calendar;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.AppointmentMonthCalendarDTO;
import com.isa.FishingBooker.model.Period;
@Component
public class StandardPeriodCalendarMapper {
	public List<AppointmentMonthCalendarDTO> convertToDtos(List<Period> entities, LocalDate start, LocalDate end) {
		List<AppointmentMonthCalendarDTO> list=new ArrayList<AppointmentMonthCalendarDTO>();
		LocalDateTime startLocal=start.atStartOfDay();
		LocalDateTime endLocal=end.plusDays(1).atStartOfDay();
		for (; startLocal.isBefore(endLocal); startLocal=startLocal.plusDays(1)) {
			list.add(new AppointmentMonthCalendarDTO(Date.valueOf(startLocal.toLocalDate()),calculateReservedHours(startLocal,entities)));
		}
		return list;
	}

	private boolean calculateReservedHours(LocalDateTime start, List<Period> entities) {
		return entities.stream().anyMatch(app->app.inPeriod(start));
	}
}