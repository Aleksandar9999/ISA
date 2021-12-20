package com.isa.FishingBooker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.FishingBooker.exceptions.InvalidPeriodDefinitionException;

@Entity
public class Period {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "start_date")
	private final Date startDate;
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
}