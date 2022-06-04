package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.exceptions.TutorservicePeriodException;

@Entity
public class BoatAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
	public BoatAppointment() {
	}
	
	public BoatAppointment(int id) {
		super(id);
	}

	@Override
	public double getCancelPercentage() {
		return boat.getCancelPercentage();
	}

	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	@Override
	public User getOwner() {
		return boat.getBoatOwner();
	}

	@JsonIgnore
	public Integer getBoatOwnerId() {
		return boat.getBoatOwner().getId();
	}

	@Override
	public double getPriceCanceled() {
		return this.getPrice()*(boat.getCancelPercentage()/100);
	}
	
	public void validateNewBoatAppointmentPeriod(Period newPeriod) {
		if (this.getPeriod().getStartDate().before(Timestamp.from(Instant.now()))
				|| this.getPeriod().getEndDate().before(Timestamp.from(Instant.now())))
			throw new TutorservicePeriodException();
		Set<Period> standardPeriods = this.getBoat().getAvailable();
		for (Period period : standardPeriods) {
			period.periodBetweenPeriod(newPeriod);
		}
	}


}
