package com.isa.FishingBooker.mapper.calendar;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.YearCalendarDTO;
import com.isa.FishingBooker.dto.AppointmentMonthCalendarDTO;
import com.isa.FishingBooker.model.Appointment;
@Component
public class MonthCalendarMapper<E extends Appointment> {
	public List<AppointmentMonthCalendarDTO> convertToDtos(List<E> entities, LocalDate start, LocalDate end) {
		List<AppointmentMonthCalendarDTO> list=new ArrayList<AppointmentMonthCalendarDTO>();
		LocalDateTime startLocal=start.atStartOfDay();
		LocalDateTime endLocal=end.plusDays(1).atStartOfDay();
		for (; startLocal.isBefore(endLocal); startLocal=startLocal.plusDays(1)) {
			list.add(new AppointmentMonthCalendarDTO(Date.valueOf(startLocal.toLocalDate()),calculateReservedHours(startLocal,entities)));
		}
		return list;
	}

	private boolean calculateReservedHours(LocalDateTime start, List<E> entities) {
		return entities.stream().anyMatch(app->app.inPeriod(start));
	}
}
