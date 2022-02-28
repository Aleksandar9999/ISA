package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
	private Timestamp startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_date")
	private Timestamp endDate;

	public Period() {
		this.startDate = null;
		this.endDate = null;
		this.id=null;
	}

	public void setId(int id) {this.id=id;}
	@JsonCreator
	public Period(@JsonProperty("startDate") Timestamp startDate, @JsonProperty("endDate") Timestamp endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		validateDates();
	}

	@JsonCreator
	public Period(@JsonProperty("id") int id, @JsonProperty("startDate") Timestamp startDate,
			@JsonProperty("endDate") Timestamp endDate) {
		this(startDate, endDate);
		//this.id = id;
	}

	private void validateDates() {
		//System.out.println("Validacija perioda");
		if (startDate == null || endDate == null)
			return;
		if (startDate.after(endDate))
			throw new InvalidPeriodDefinitionException();
	}

	public static boolean isSameDate(Timestamp s1, Timestamp s2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		return formatter.format(s1).equals(formatter.format(s2));
	}

	public boolean areStartEndAreSameDate() {
		return isSameDate(startDate, endDate);
	}

	public void setStartDate(Timestamp start) {
		this.startDate = start;
	}

	public Integer getId() {
		return id;
	}

	public void setEndDate(Timestamp end) {
		this.endDate = end;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public boolean inPeriod(LocalDateTime date) {
		LocalDateTime end = endDate.toLocalDateTime();
		LocalDateTime startLocal = startDate.toLocalDateTime();

		return (startLocal.isBefore(date) && end.isAfter(date)) || startLocal.toLocalDate().isEqual(date.toLocalDate())
				|| end.isEqual(date);
	}

	public boolean inPeriod(Timestamp date) {
		if(this.endDate==null) {
			return this.startDate.before(date);
		}
		return inPeriod(date.toLocalDateTime());
	}

	public static Period createPeriod(Timestamp start, int duration) {
		Calendar calendar = new Calendar.Builder().build();
		calendar.setTime(start);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, duration);
		return new Period(Timestamp.from(startDate.toInstant()), Timestamp.from(calendar.toInstant()));
	}

	public void overlap(Period newPeriod) {
		periodStartOrEndSameTime(newPeriod);
		periodBetweenPeriod(newPeriod);
		endDateBetweenPeriod(newPeriod);
		startDateBetweenPeriod(newPeriod);
	}

	private void periodStartOrEndSameTime(Period newPeriod) {
		if (newPeriod.getStartDate().equals(startDate) || newPeriod.getEndDate().equals(startDate))
			throw new PeriodOverlapException();
	}

	private void startDateBetweenPeriod(Period newPeriod) {
		if (newPeriod.getStartDate().after(startDate) && newPeriod.getStartDate().before(endDate))
			throw new PeriodOverlapException();
	}

	private void endDateBetweenPeriod(Period newPeriod) {
		if (newPeriod.getEndDate().after(startDate) && newPeriod.getEndDate().before(endDate))
			throw new PeriodOverlapException();
	}

	public void periodBetweenPeriod(Period newPeriod) {
		if (newPeriod.getStartDate().after(this.startDate) && newPeriod.getEndDate().before(endDate))
			throw new PeriodOverlapException();
	}
}