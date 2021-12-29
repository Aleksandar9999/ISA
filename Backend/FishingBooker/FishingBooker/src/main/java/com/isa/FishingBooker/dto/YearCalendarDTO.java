package com.isa.FishingBooker.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class YearCalendarDTO {
	private double duration;
	private Date date;
	public YearCalendarDTO() {
		// TODO Auto-generated constructor stub
	}

	public YearCalendarDTO(double duration, Date date) {
		super();
		this.duration = duration;
		this.date = date;
	}

	public String getDate() {
		SimpleDateFormat formater=new SimpleDateFormat("MMMM");
		return formater.format(date);
	}
	
	public String getDuration() {
		return "Days" + duration;
	}
}
