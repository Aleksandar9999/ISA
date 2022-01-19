package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.FishingBooker.exceptions.InvalidPeriodDefinitionException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;

@Entity
public class Period {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "start_date")
	private final Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_date")
	private final Date endDate;

	public Period() {
		this.startDate = new Date();
		this.endDate = new Date();
	}

	@JsonCreator
	public Period(@JsonProperty("startDate") Date startDate, @JsonProperty("endDate") Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		validateDates();
	}

	@JsonCreator
	public Period(@JsonProperty("id")int id, @JsonProperty("startDate") Date startDate, @JsonProperty("endDate") Date endDate) {
		this(startDate,endDate);
		this.id=id;
	}
	
	private void validateDates() {
		System.out.println("Validacija perioda");
		if (startDate == null || endDate == null)
			return;
		if (startDate.after(endDate))
			throw new InvalidPeriodDefinitionException();
	}

	public Integer getId() {
		return id;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}
	
	public static Period createPeriod(Timestamp start, int duration) {
		Calendar calendar = new Calendar.Builder().build();
		calendar.setTime(start);
		Date startDate=calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, duration);
		return new Period(startDate,calendar.getTime());
	}
	
	public void overlap(Period newPeriod) {
		periodStartOrEndSameTime(newPeriod);
		periodBetweenPeriod(newPeriod);
		endDateBetweenPeriod(newPeriod);
		startDateBetweenPeriod(newPeriod);
	}

	private void periodStartOrEndSameTime(Period newPeriod) {
		if(newPeriod.getStartDate().equals(startDate) || newPeriod.getEndDate().equals(startDate))
			throw new PeriodOverlapException();
	}

	private void startDateBetweenPeriod(Period newPeriod) {
		if(newPeriod.getStartDate().after(startDate) && newPeriod.getStartDate().before(endDate))
			throw new PeriodOverlapException();
	}

	private void endDateBetweenPeriod(Period newPeriod) {
		if(newPeriod.getEndDate().after(startDate) && newPeriod.getEndDate().before(endDate)) 
			throw new PeriodOverlapException();
	}

	private void periodBetweenPeriod(Period newPeriod) {
		if(newPeriod.getStartDate().after(this.startDate) && newPeriod.getEndDate().before(endDate))
			throw new PeriodOverlapException();
	} 
}