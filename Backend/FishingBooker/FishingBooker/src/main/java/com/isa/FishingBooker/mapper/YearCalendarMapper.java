package com.isa.FishingBooker.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.YearCalendarDTO;
import com.isa.FishingBooker.model.Appointment;

@Component
public class YearCalendarMapper<E extends Appointment>{
	
	public List<YearCalendarDTO> convertToDtos(List<E> entities, int year) {
		//List<YearCalendarDTO> search = ((YearCalendarDTO) this.getRepository()).search(quaryParams);
		List<YearCalendarDTO> list=new ArrayList<YearCalendarDTO>();
		LocalDate firstInYear=LocalDate.of(year, 1, 1);
		LocalDate lastInYear=LocalDate.of(year, 12, 30);
		for (; firstInYear.isBefore(lastInYear); firstInYear=firstInYear.plusMonths(1)) {
			list.add(new YearCalendarDTO(calculateReservedDays(Date.valueOf(firstInYear), entities),Date.valueOf(firstInYear)));
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	private double calculateReservedDays(Date date, List<E> entities) {
		return entities.stream().filter(item-> item.getStart().getMonth()==date.getMonth()).mapToDouble(Appointment::getDuration).sum();
	}
}
