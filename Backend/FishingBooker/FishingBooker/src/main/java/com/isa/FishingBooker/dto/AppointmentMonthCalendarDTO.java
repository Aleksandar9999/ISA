package com.isa.FishingBooker.dto;

import java.sql.Date;

public class AppointmentMonthCalendarDTO {
	private Date date;
	private boolean reservedHours;
	public AppointmentMonthCalendarDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public AppointmentMonthCalendarDTO(Date date, boolean reservedHours) {
		super();
		this.date = date;
		this.reservedHours = reservedHours;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isReservedHours() {
		return reservedHours;
	}
	public void setReservedHours(boolean reservedHours) {
		this.reservedHours = reservedHours;
	}
}
