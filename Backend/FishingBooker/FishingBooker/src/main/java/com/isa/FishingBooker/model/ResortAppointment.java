package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.isa.FishingBooker.exceptions.TutorservicePeriodException;

@Entity
public class ResortAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name = "resort_id")
	private Resort resort;

	public ResortAppointment() {
		// TODO Auto-generated constructor stub
	}

	public ResortAppointment(int id) {
		super(id);
	}

	@Override
	public double getCancelPercentage() {
		return 0;
	}

	public Resort getResort() {
		return resort;
	}

	public void setResort(Resort resort) {
		this.resort = resort;
	}

	@Override
	public User getOwner() {
		return resort.getResortOwner();
	}

	@Override
	public double getPriceCanceled() {
		return 0;
	}
	
	public void validateNewResortAppointmentPeriod(Period newPeriod) {
		if (this.getPeriod().getStartDate().before(Timestamp.from(Instant.now()))
				|| this.getPeriod().getEndDate().before(Timestamp.from(Instant.now())))
			throw new TutorservicePeriodException();
		Set<Period> standardPeriods = this.getResort().getResortAvailable();
		for (Period period : standardPeriods) {
			period.periodBetweenPeriod(newPeriod);
		}
	}

}
